from rest_framework import generics, status, viewsets
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework.decorators import api_view, permission_classes, action
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.exceptions import TokenError
from django.contrib.auth import authenticate, get_user_model
from django.utils import timezone
from .serializers import UserSerializer, SignUpSerializer, ChangePasswordSerializer
from .permissions import IsAdminUser, IsStaffUser

User = get_user_model()


class UserDetailView(generics.RetrieveUpdateAPIView):
    """User detail view for retrieving and updating authenticated user's information."""
    serializer_class = UserSerializer
    permission_classes = [IsAuthenticated]

    def get_object(self):
        return self.request.user


@api_view(['POST'])
@permission_classes([AllowAny])
def signup(request):
    """Register a new user."""
    serializer = SignUpSerializer(data=request.data)
    serializer.is_valid(raise_exception=True)
    user = serializer.save()
    refresh = RefreshToken.for_user(user)
    return Response({
        'message': 'User registered successfully',
        'user': UserSerializer(user).data,
        'tokens': {
            'refresh': str(refresh),
            'access': str(refresh.access_token),
        }
    }, status=status.HTTP_201_CREATED)


@api_view(['POST'])
@permission_classes([AllowAny])
def login(request):
    """Authenticate a user and return tokens."""
    email = request.data.get('email')
    password = request.data.get('password')
    user = authenticate(request, email=email, password=password)

    if user is None:
        return Response({'message': 'Invalid credentials'}, status=status.HTTP_401_UNAUTHORIZED)

    if not user.is_active:
        return Response({"message": "This account is not active"}, status=status.HTTP_403_FORBIDDEN)

    user.last_login = timezone.now()
    user.save(update_fields=['last_login'])

    refresh = RefreshToken.for_user(user)
    return Response({
        'message': 'Login successful',
        'user': UserSerializer(user).data,
        'tokens': {
            'refresh': str(refresh),
            'access': str(refresh.access_token),
        }
    }, status=status.HTTP_200_OK)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def logout(request):
    """Blacklist the user's refresh token."""
    try:
        refresh_token = request.data.get("refresh")
        token = RefreshToken(refresh_token)
        token.blacklist()
        return Response({"message": "Successfully logged out"}, status=status.HTTP_200_OK)
    except (KeyError, TokenError):
        return Response({"message": "Invalid or missing refresh token"}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def change_password(request):
    """Change the authenticated user's password."""
    serializer = ChangePasswordSerializer(data=request.data, context={'request': request})
    serializer.is_valid(raise_exception=True)
    serializer.save()
    return Response({"message": "Password changed successfully"}, status=status.HTTP_200_OK)


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def deactivate(request):
    """Deactivate the authenticated user's account."""
    user = request.user
    user.is_active = False
    user.save()
    return Response({"message": f"User {user.email} has been deactivated"}, status=status.HTTP_200_OK)


class UserViewSet(viewsets.ModelViewSet):
    """ViewSet for managing user accounts."""
    queryset = User.objects.all()
    serializer_class = UserSerializer
    
    def get_permissions(self):
        if self.action in ['list', 'retrieve']:
            permission_classes = [IsStaffUser]
        elif self.action in ['create', 'update', 'partial_update', 'destroy']:
            permission_classes = [IsAdminUser]
        else:
            permission_classes = [IsAdminUser]
        return [permission() for permission in permission_classes]

    def get_queryset(self):
        if self.request.user.role == User.ADMIN:
            return User.objects.all()
        return User.objects.filter(is_active=True)

    def perform_destroy(self, instance):
        instance.is_active = False
        instance.save()

    @action(detail=True, methods=['post'], permission_classes=[IsAdminUser])
    def activate(self, request, pk=None):
        """Activate a user account."""
        user = self.get_object()
        user.is_active = True
        user.save()
        return Response({"message": f"User {user.email} has been activated"})

    @action(detail=True, methods=['post'], permission_classes=[IsAdminUser])
    def deactivate(self, request, pk=None):
        """Deactivate a user account."""
        user = self.get_object()
        user.is_active = False
        user.save()
        return Response({"message": f"User {user.email} has been deactivated"})

    def update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', False)
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=partial)
        serializer.is_valid(raise_exception=True)

        if 'role' in serializer.validated_data and not request.user.role == User.ADMIN:
            return Response({"message": "Only admin can change user role"}, status=status.HTTP_403_FORBIDDEN)

        self.perform_update(serializer)
        return Response(serializer.data)