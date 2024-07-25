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
router.register(r"category-assets", CategoryAssetViewSet)
router.register(r"warehouses", WareHouseViewSet)
router.register(r"appraisers", AppraiserViewSet)
router.register(r"assets", AssetViewSet)
router.register(r"asset-media", AssetMediaViewSet)
router.register(r"inventory-transactions", InventoryTransactionViewSet)

urlpatterns = [
    path("", include(router.urls)),
]
