from django.urls import path, include
from .views import (CategoryAssetViewSet, WareHouseViewSet, AppraiserViewSet,
                    AssetViewSet, AssetMediaViewSet, InventoryTransactionViewSet)
from rest_framework.routers import DefaultRouter
router = DefaultRouter()
router.register('assets', AssetViewSet, basename='assets')

urlpatterns = [
    path('', include(router.urls)),
]