from django.db import models
from users.models import User
from .enums import AssetStatus, AppraiserStatus, AssetAppraiseStatus

class Appraiser(models.Model):
    experiences = models.TextField(null=True, blank=True)
    appraiser_status = models.CharField(max_length=50, choices=AppraiserStatus.choices, default=AppraiserStatus.ACTIVE)
    id_user = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class Asset(models.Model):
    asset_name = models.CharField(max_length=255)
    description = models.TextField()
    type_asset = models.CharField(max_length=255)
    status = models.BooleanField(default=True)
    size = models.CharField(max_length=100)
    origin = models.CharField(max_length=255)
    asset_status = models.CharField(max_length=50, choices=AssetStatus.choices)
    appraise_asset_status = models.CharField(max_length=50, choices=AssetAppraiseStatus.choices, default=AssetAppraiseStatus.NOT_APPRAISED)
    init_price = models.PositiveIntegerField()
    quantity = models.PositiveIntegerField()
    id_user_winner = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, related_name='won_assets')
    id_seller = models.ForeignKey(User, on_delete=models.CASCADE, related_name='sold_assets')
    id_warehouse = models.ForeignKey('WareHouse', on_delete=models.SET_NULL, null=True)
    id_appraiser = models.ForeignKey(Appraiser, on_delete=models.SET_NULL, null=True, related_name='appraiser_assets')
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class AssetMedia(models.Model):
    id_asset = models.ForeignKey(Asset, on_delete=models.CASCADE)
    media_file = models.FileField(upload_to="asset_media/", blank=True, null=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class WareHouse(models.Model):
    warehouse_name = models.CharField(max_length=255)
    warehouse_address = models.TextField()
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)