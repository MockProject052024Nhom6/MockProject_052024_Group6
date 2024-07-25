from django.urls import path, include
from rest_framework.routers import DefaultRouter
from . import views

router = DefaultRouter()
router.register('auctions', views.AuctionViewSet)
router.register('user-has-auctions', views.UserHasAuctionViewSet)
router.register('holidays', views.HolidayViewSet)
router.register('auction-has-assets', views.AuctionHasAssetViewSet)
router.register('bids', views.BidViewSet)
router.register('fees', views.FeeViewSet)
router.register('contracts', views.ContractViewSet)
router.register('taxes', views.TaxViewSet)
router.register('tax-has-contracts', views.TaxHasContractViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
