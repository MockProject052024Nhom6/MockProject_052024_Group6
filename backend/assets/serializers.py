from rest_framework import serializers
from .models import Appraiser, Asset, AssetMedia, WareHouse

class AppraiserSerializer(serializers.ModelSerializer):
    class Meta:
        model = Appraiser
        fields = ['id', 'user', 'experiences', 'status', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class AssetMediaSerializer(serializers.ModelSerializer):
    class Meta:
        model = AssetMedia
        fields = ['id', 'asset', 'media_type', 'file', 'is_primary', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class AssetSerializer(serializers.ModelSerializer):
    media = AssetMediaSerializer(many=True, read_only=True)

    class Meta:
        model = Asset
        fields = ['id', 'name', 'description', 'asset_type', 'size', 'origin', 'status', 'appraise_status', 
                  'initial_price', 'quantity', 'seller', 'winner', 'warehouse', 'appraiser', 'appraised_value', 
                  'appraisal_date', 'created_date', 'modified_date', 'media']
        read_only_fields = ['id', 'created_date', 'modified_date']
    
class WareHouseSerializer(serializers.ModelSerializer):
    class Meta:
        model = WareHouse
        fields = ['id', 'name', 'address', 'capacity', 'current_occupancy', 'is_active', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']