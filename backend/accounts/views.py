from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.authtoken.models import Token
from rest_framework.permissions import IsAuthenticated
from django.contrib.auth import authenticate
from .serializers import UserSerializer, RegisterSerializer

class RegisterAPI(APIView):
    def post(self, request):
        serializer = RegisterSerializer(data=request.data)
        if serializer.is_valid():
            user = serializer.save()
            token, created = Token.objects.get_or_create(user=user)
            return Response({
                "user": UserSerializer(user).data,
                "token": token.key
            }, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class LoginAPI(APIView):
    def post(self, request):
        username = request.data.get('username')
        password = request.data.get('password')
        user = authenticate(username=username, password=password)
        if user:
            token, created = Token.objects.get_or_create(user=user)
            return Response({
                "user": UserSerializer(user).data,
                "token": token.key
            })
        return Response({"error": "Invalid credentials"}, status=status.HTTP_400_BAD_REQUEST)

class LogoutAPI(APIView):
    permission_classes = [IsAuthenticated]

    def post(self, request):
        request.user.auth_token.delete()
        return Response({"message": "Successfully logged out."}, status=status.HTTP_200_OK)