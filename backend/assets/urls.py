from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import (
    CategoryAssetViewSet,
    WareHouseViewSet,
    AppraiserViewSet,
    AssetViewSet,
    AssetMediaViewSet,
    InventoryTransactionViewSet,
)

router = DefaultRouter()
router.register("category-assets", CategoryAssetViewSet)
router.register("warehouses", WareHouseViewSet)
router.register("appraisers", AppraiserViewSet)
router.register("assets", AssetViewSet)
router.register("asset-media", AssetMediaViewSet)
router.register("inventory-transactions", InventoryTransactionViewSet)

urlpatterns = [
    path("", include(router.urls)),
]
