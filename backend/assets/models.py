from django.db import models
from users.models import User
from .enums import PropertyStatus, AssetStatus, AppraiserStatus, AssetMediaType

class CategoryAsset(models.Model):
    name = models.CharField(max_length=255)

class WareHouse(models.Model):
    warehouse_name = models.CharField(max_length=255)
    address_warehouse = models.TextField()
    asset_placement = models.TextField()
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class Appraiser(models.Model):
    experiences = models.TextField()
    specialized = models.CharField(max_length=255)
    status_appraiser = models.CharField(max_length=50, choices=AppraiserStatus.choices, default=AppraiserStatus.ACTIVE)
    id_user = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class Asset(models.Model):
    asset_name = models.CharField(max_length=255)
    description = models.TextField()
    status = models.BooleanField(default=True)
    size = models.CharField(max_length=100)
    origin = models.CharField(max_length=255)
    property_status = models.CharField(max_length=50, choices=PropertyStatus.choices, default=PropertyStatus.AVAILABLE)
    quantity = models.PositiveIntegerField()
    status_asset = models.CharField(max_length=50, choices=AssetStatus.choices, default=AssetStatus.ACTIVE)
    id_user_winner = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, related_name='won_assets')
    id_seller = models.ForeignKey(User, on_delete=models.CASCADE, related_name='sold_assets')
    id_warehouse = models.ForeignKey(WareHouse, on_delete=models.SET_NULL, null=True)
    id_appraiser = models.ForeignKey(Appraiser, on_delete=models.SET_NULL, null=True, related_name='appraiser_assets')
    id_category = models.ForeignKey(CategoryAsset, on_delete=models.SET_NULL, null=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class AssetMedia(models.Model):
    id_asset = models.ForeignKey(Asset, on_delete=models.CASCADE)
    media_file = models.FileField(upload_to="media/", blank=True, null=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class InventoryTransaction(models.Model):
    id_warehouse = models.ForeignKey(WareHouse, on_delete=models.CASCADE)
    import_date = models.DateTimeField(null=True, blank=True)
    export_date = models.DateTimeField(null=True, blank=True)
    description = models.TextField()
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)
