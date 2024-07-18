from rest_framework import serializers
from .models import Auction, UserHasAuction, Holiday, AuctionHasAsset, Bid, Fee, Contract, Tax, TaxHasContract
from users.models import User
from assets.models import Asset

class SimpleUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'email', 'first_name', 'last_name']

class SimpleAssetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Asset
        fields = ['id', 'asset_name', 'description']

class AuctionSerializer(serializers.ModelSerializer):
    id_auctioneer = SimpleUserSerializer(read_only=True)

    class Meta:
        model = Auction
        fields = '__all__'

class UserHasAuctionSerializer(serializers.ModelSerializer):
    id_auction = AuctionSerializer(read_only=True)
    id_user = SimpleUserSerializer(read_only=True)

    class Meta:
        model = UserHasAuction
        fields = '__all__'

class HolidaySerializer(serializers.ModelSerializer):
    class Meta:
        model = Holiday
        fields = '__all__'

class AuctionHasAssetSerializer(serializers.ModelSerializer):
    id_auction = AuctionSerializer(read_only=True)
    id_asset = SimpleAssetSerializer(read_only=True)

    class Meta:
        model = AuctionHasAsset
        fields = '__all__'

class BidSerializer(serializers.ModelSerializer):
    id_user = SimpleUserSerializer(read_only=True)
    id_auction_has_asset = AuctionHasAssetSerializer(read_only=True)

    class Meta:
        model = Bid
        fields = '__all__'

class FeeSerializer(serializers.ModelSerializer):
    id_auction_has_asset = AuctionHasAssetSerializer(read_only=True)

    class Meta:
        model = Fee
        fields = '__all__'

class ContractSerializer(serializers.ModelSerializer):
    id_auction_has_asset = AuctionHasAssetSerializer(read_only=True)
    id_user = SimpleUserSerializer(read_only=True)

    class Meta:
        model = Contract
        fields = '__all__'

class TaxSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tax
        fields = '__all__'

class TaxHasContractSerializer(serializers.ModelSerializer):
    id_tax = TaxSerializer(read_only=True)
    id_contract = ContractSerializer(read_only=True)

    class Meta:
        model = TaxHasContract
        fields = '__all__'