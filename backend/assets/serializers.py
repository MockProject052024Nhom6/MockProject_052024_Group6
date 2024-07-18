from rest_framework import serializers
from .models import CategoryAsset, WareHouse, Appraiser, Asset, AssetMedia, InventoryTransaction
from users.serializers import UserSerializer

class CategoryAssetSerializer(serializers.ModelSerializer):
    class Meta:
        model = CategoryAsset
        fields = '__all__'

class WareHouseSerializer(serializers.ModelSerializer):
    class Meta:
        model = WareHouse
        fields = '__all__'

class AppraiserSerializer(serializers.ModelSerializer):
    id_user = UserSerializer(read_only=True)

    class Meta:
        model = Appraiser
        fields = '__all__'

class AssetSerializer(serializers.ModelSerializer):
    id_user_winner = UserSerializer(read_only=True)
    id_seller = UserSerializer(read_only=True)
    id_warehouse = WareHouseSerializer(read_only=True)
    id_appraiser = AppraiserSerializer(read_only=True)
    id_category = CategoryAssetSerializer(read_only=True)

    class Meta:
        model = Asset
        fields = '__all__'

class AssetMediaSerializer(serializers.ModelSerializer):
    id_asset = AssetSerializer(read_only=True)

    class Meta:
        model = AssetMedia
        fields = '__all__'

class InventoryTransactionSerializer(serializers.ModelSerializer):
    id_warehouse = WareHouseSerializer(read_only=True)

    class Meta:
        model = InventoryTransaction
        fields = '__all__'