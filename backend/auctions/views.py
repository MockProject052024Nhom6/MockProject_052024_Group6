from rest_framework import viewsets, permissions, status
from rest_framework.decorators import action
from rest_framework.response import Response
from django.utils import timezone
from django.db import transaction
from users.permissions import IsAdminUser, IsStaffUser
from users.models import User
from assets.models import Asset
from .models import Auction, UserHasAuction, Holiday, AuctionHasAsset, Bid, Fee, Contract, Tax, TaxHasContract
from .serializers import AuctionSerializer, UserHasAuctionSerializer, HolidaySerializer, AuctionHasAssetSerializer, BidSerializer, FeeSerializer, ContractSerializer, TaxSerializer, TaxHasContractSerializer
from .enums import UserHasAuctionStatus
from assets.enums import AssetStatus, PropertyStatus


class AuctionViewSet(viewsets.ModelViewSet):
    queryset = Auction.objects.all()
    serializer_class = AuctionSerializer

    def get_permissions(self):
        if self.action in ['list', 'retrieve']:
            permission_classes = [permissions.AllowAny]
        elif self.action in ['create', 'update', 'partial_update', 'destroy', 'add_asset_to_auction']:
            permission_classes = [IsStaffUser]
        else:
            permission_classes = [permissions.IsAuthenticated]
        return [permission() for permission in permission_classes]

    @action(detail=True, methods=['post'])
    @transaction.atomic
    def add_asset_to_auction(self, request, pk=None):
        auction = self.get_object()
        asset_id = request.data.get('asset_id')
        starting_price = request.data.get('starting_price')

        if not asset_id or not starting_price:
            return Response({"error": "Asset ID and starting price are required"}, status=status.HTTP_400_BAD_REQUEST)

        try:
            asset = Asset.objects.get(id=asset_id)
        except Asset.DoesNotExist:
            return Response({"error": "Asset not found"}, status=status.HTTP_404_NOT_FOUND)

        if asset.status_asset != AssetStatus.ACTIVE or asset.property_status != PropertyStatus.AVAILABLE:
            return Response({"error": "Asset must be active and available"}, status=status.HTTP_400_BAD_REQUEST)

        auction_has_asset = AuctionHasAsset.objects.create(
            id_auction=auction,
            id_asset=asset,
            starting_price=starting_price,
            current_price=starting_price
        )

        serializer = AuctionHasAssetSerializer(auction_has_asset)
        return Response(serializer.data, status=status.HTTP_201_CREATED)

    @action(detail=True, methods=['post'])
    def join_auction(self, request, pk=None):
        auction = self.get_object()
        user = request.user
        deposits = request.data.get('deposits')
        
        if not deposits:
            return Response({"error": "Deposits amount is required"}, status=status.HTTP_400_BAD_REQUEST)

        user_has_auction, created = UserHasAuction.objects.get_or_create(
            id_auction=auction,
            id_user=user,
            defaults={
                'deposits': deposits,
                'deposits_date': timezone.now(),
                'user_has_auction_status': UserHasAuctionStatus.JOIN
            }
        )
        
        if created:
            auction.number_of_participants += 1
            auction.save()
        
        serializer = UserHasAuctionSerializer(user_has_auction)
        return Response(serializer.data, status=status.HTTP_201_CREATED)


class UserHasAuctionViewSet(viewsets.ModelViewSet):
    queryset = UserHasAuction.objects.all()
    serializer_class = UserHasAuctionSerializer
    permission_classes = [IsAdminUser | IsStaffUser]

class HolidayViewSet(viewsets.ModelViewSet):
    queryset = Holiday.objects.all()
    serializer_class = HolidaySerializer
    permission_classes = [IsAdminUser | IsStaffUser]

class AuctionHasAssetViewSet(viewsets.ModelViewSet):
    queryset = AuctionHasAsset.objects.all()
    serializer_class = AuctionHasAssetSerializer
    permission_classes = [permissions.IsAuthenticated]

class BidViewSet(viewsets.ModelViewSet):
    queryset = Bid.objects.all()
    serializer_class = BidSerializer
    permission_classes = [permissions.IsAuthenticated]

    def perform_create(self, serializer):
        bid = serializer.save(id_user=self.request.user)
        auction_has_asset = bid.id_auction_has_asset
        if bid.bid_amount > auction_has_asset.current_price:
            auction_has_asset.current_price = bid.bid_amount
            auction_has_asset.save()

    @action(detail=False, methods=['get'])
    def my_bids(self, request):
        bids = Bid.objects.filter(id_user=request.user)
        serializer = self.get_serializer(bids, many=True)
        return Response(serializer.data)

class FeeViewSet(viewsets.ModelViewSet):
    queryset = Fee.objects.all()
    serializer_class = FeeSerializer
    permission_classes = [IsAdminUser | IsStaffUser]

class ContractViewSet(viewsets.ModelViewSet):
    queryset = Contract.objects.all()
    serializer_class = ContractSerializer
    permission_classes = [IsAdminUser | IsStaffUser]

class TaxViewSet(viewsets.ModelViewSet):
    queryset = Tax.objects.all()
    serializer_class = TaxSerializer
    permission_classes = [IsAdminUser]

class TaxHasContractViewSet(viewsets.ModelViewSet):
    queryset = TaxHasContract.objects.all()
    serializer_class = TaxHasContractSerializer
    permission_classes = [IsAdminUser | IsStaffUser]