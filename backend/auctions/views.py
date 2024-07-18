from rest_framework import viewsets, permissions, status
from rest_framework.decorators import action
from rest_framework.response import Response
from .models import Auction, UserHasAuction, Holiday, AuctionHasAsset, Bid, Fee, Contract, Tax, TaxHasContract
from .serializers import AuctionSerializer, UserHasAuctionSerializer, HolidaySerializer, AuctionHasAssetSerializer, BidSerializer, FeeSerializer, ContractSerializer, TaxSerializer, TaxHasContractSerializer
from users.permissions import IsAdminUser, IsStaffUser
from users.models import User

class AuctionViewSet(viewsets.ModelViewSet):
    queryset = Auction.objects.all()
    serializer_class = AuctionSerializer
    
    def get_permissions(self):
        if self.action in ['list', 'retrieve']:
            permission_classes = [permissions.IsAuthenticated]
        else:
            permission_classes = [IsStaffUser]
        return [permission() for permission in permission_classes]

    @action(detail=True, methods=['post'], permission_classes=[permissions.IsAuthenticated])
    def participate(self, request, pk=None):
        auction = self.get_object()
        user = request.user
        UserHasAuction.objects.get_or_create(user=user, auction=auction)
        return Response({"message": "Successfully joined the auction"}, status=status.HTTP_200_OK)