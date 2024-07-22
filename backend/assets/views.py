from rest_framework import viewsets, permissions, status
from rest_framework.response import Response
from users.permissions import IsAdminUser, IsStaffUser
from users.models import User

from rest_framework import viewsets, permissions
from rest_framework.decorators import action
from rest_framework.response import Response
from .models import (
    CategoryAsset,
    WareHouse,
    Appraiser,
    Asset,
    AssetMedia,
    InventoryTransaction,
)
from .serializers import (
    CategoryAssetSerializer,
    WareHouseSerializer,
    AppraiserSerializer,
    AssetSerializer,
    AssetMediaSerializer,
    InventoryTransactionSerializer,
)
from users.permissions import IsAdminUser, IsStaffUser


class CategoryAssetViewSet(viewsets.ModelViewSet):
    """ViewSet for managing asset categories."""

    queryset = CategoryAsset.objects.all()
    serializer_class = CategoryAssetSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [
                IsStaffUser
            ]  # Only staff or admin can modify categories
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]


class WareHouseViewSet(viewsets.ModelViewSet):
    """ViewSet for managing warehouses."""

    queryset = WareHouse.objects.all()
    serializer_class = WareHouseSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [
                IsStaffUser
            ]  # Only staff or admin can modify warehouses
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]


class AppraiserViewSet(viewsets.ModelViewSet):
    """ViewSet for managing appraisers."""

    queryset = Appraiser.objects.all()
    serializer_class = AppraiserSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            # Only admin can manage appraisers
            permission_classes = [IsAdminUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]


class AssetViewSet(viewsets.ModelViewSet):
    """ViewSet for managing assets."""

    queryset = Asset.objects.all()
    serializer_class = AssetSerializer
    
    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [permissions.IsAuthenticated, IsAdminUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]

    def perform_create(self, serializer):
        serializer.save(id_seller=self.request.user)
        
    @action(detail=True, methods=["post"], url_path='add-media', permission_classes=[permissions.IsAuthenticated])
    def add_media(self, request, pk=None):
        """Add media to an asset."""
        asset = self.get_object()
        serializer = AssetMediaSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save(id_asset=asset)
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)
    


class InventoryTransactionViewSet(viewsets.ModelViewSet):
    """ViewSet for managing inventory transactions."""

    queryset = InventoryTransaction.objects.all()
    serializer_class = InventoryTransactionSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [
                IsStaffUser
            ]  # Only staff or admin can modify inventory transactions
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]
