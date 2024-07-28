from rest_framework import viewsets, permissions, status
from rest_framework.decorators import action
from rest_framework.response import Response
from django.db.models import Count
from .models import (
    WareHouse,
    Appraiser,
    Asset,
    AssetMedia,
)
from .serializers import (
    WareHouseSerializer,
    AppraiserSerializer,
    AssetSerializer,
    AssetMediaSerializer,
)
from users.permissions import IsAdminUser, IsStaffUser
from users.enums import UserRole


class AssetViewSet(viewsets.ModelViewSet):
    """ViewSet for managing assets."""

    queryset = Asset.objects.all()
    serializer_class = AssetSerializer

    def get_permissions(self):
        if self.action in [
            "list",
            "retrieve",
            "assets_by_category",
            "category_assets_count",
            "assets_by_specific_category",
            "create",
        ]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["update", "partial_update", "destroy", "update_status"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action == "assets_by_seller":
            permission_classes = [IsStaffUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]

    def perform_create(self, serializer):
        serializer.save(id_seller=self.request.user)

    def perform_update(self, serializer):
        instance = self.get_object()
        if (
            instance.id_seller == self.request.user
            or self.request.user.role == UserRole.ADMIN
        ):
            serializer.save()
        else:
            raise permissions.PermissionDenied(
                "You don't have permission to edit this asset."
            )

    def perform_destroy(self, instance):
        if (
            instance.id_seller == self.request.user
            or self.request.user.role == UserRole.ADMIN
        ):
            instance.delete()
        else:
            raise permissions.PermissionDenied(
                "You don't have permission to delete this asset."
            )

    @action(detail=False, methods=["get"])
    def assets_by_category(self, request):
        assets = Asset.objects.all().select_related("id_category")
        serializer = self.get_serializer(assets, many=True)
        return Response(serializer.data)

    @action(detail=False, methods=["get"], url_path="category/(?P<category>[^/]+)")
    def assets_by_specific_category(self, request, category=None):
        assets = Asset.objects.filter(id_category__name=category)
        serializer = self.get_serializer(assets, many=True)
        return Response(serializer.data)

    @action(detail=False, methods=["get"])
    def category_assets_count(self, request):
        category_counts = CategoryAsset.objects.annotate(asset_count=Count("asset"))
        data = [
            {"category": category.name, "count": category.asset_count}
            for category in category_counts
        ]
        return Response(data)

    @action(detail=False, methods=["get"], url_path="sellers/(?P<id_seller>[0-9]+)")
    def assets_by_seller(self, request, id_seller=None):
        assets = Asset.objects.filter(id_seller=id_seller)
        serializer = self.get_serializer(assets, many=True)
        return Response(serializer.data)

    @action(
        detail=True,
        methods=["patch"],
        url_path="update-status",
        permission_classes=[permissions.IsAuthenticated],
    )
    def update_status(self, request, pk=None):
        asset = self.get_object()
        if asset.id_seller == request.user or request.user.role == UserRole.ADMIN:
            status = request.data.get("status_asset")
            if status:
                asset.status_asset = status
                asset.save()
                return Response({"status": "Asset status updated"})
            return Response(
                {"status": "Failed to update asset status"},
                status=status.HTTP_400_BAD_REQUEST,
            )
        else:
            raise permissions.PermissionDenied(
                "You don't have permission to update this asset's status."
            )

    @action(
        detail=True,
        methods=["post"],
        url_path="add-media",
        permission_classes=[permissions.IsAuthenticated],
    )
    def add_media(self, request, pk=None):
        """Add media to an asset."""
        asset = self.get_object()
        if asset.id_seller == request.user or request.user.role == UserRole.ADMIN:
            serializer = AssetMediaSerializer(data=request.data)
            if serializer.is_valid():
                serializer.save(id_asset=asset)
                return Response(serializer.data, status=201)
            return Response(serializer.errors, status=400)
        else:
            raise permissions.PermissionDenied(
                "You don't have permission to add media to this asset."
            )

    @action(
        detail=True,
        methods=["put"],
        url_path="update-media/(?P<media_id>[0-9]+)",
        permission_classes=[permissions.IsAuthenticated],
    )
    def update_media(self, request, pk=None, media_id=None):
        """Update media for an asset."""
        asset = self.get_object()
        if asset.id_seller == request.user or request.user.role == UserRole.ADMIN:
            try:
                media = AssetMedia.objects.get(id=media_id, id_asset=asset)
            except AssetMedia.DoesNotExist:
                return Response(
                    {"error": "Media not found or does not belong to this asset."},
                    status=status.HTTP_404_NOT_FOUND,
                )

            serializer = AssetMediaSerializer(media, data=request.data, partial=True)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            raise permissions.PermissionDenied(
                "You don't have permission to update media for this asset."
            )


class CategoryAssetViewSet(viewsets.ModelViewSet):
    """ViewSet for managing asset categories."""

    queryset = CategoryAsset.objects.all()
    serializer_class = CategoryAssetSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [IsStaffUser]
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
            permission_classes = [IsStaffUser]
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
            permission_classes = [IsAdminUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]


class InventoryTransactionViewSet(viewsets.ModelViewSet):
    """ViewSet for managing inventory transactions."""

    queryset = InventoryTransaction.objects.all()
    serializer_class = InventoryTransactionSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [IsStaffUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]


class AssetMediaViewSet(viewsets.ModelViewSet):
    """ViewSet for managing asset media."""

    queryset = AssetMedia.objects.all()
    serializer_class = AssetMediaSerializer

    def get_permissions(self):
        if self.action in ["list", "retrieve"]:
            permission_classes = [permissions.IsAuthenticated]
        elif self.action in ["create", "update", "partial_update", "destroy"]:
            permission_classes = [IsStaffUser]  
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]
