from django.db import models
from users.models import User
from .enums import AssetStatus, AppraiserStatus, AssetAppraiseStatus, AssetMediaType

class Appraiser(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='appraiser_profile')
    experiences = models.TextField(null=True, blank=True)
    status = models.CharField(max_length=50, choices=AppraiserStatus.choices, default=AppraiserStatus.ACTIVE)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"Appraiser: {self.user.username}"

class Asset(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField()
    asset_type = models.CharField(max_length=255)
    size = models.CharField(max_length=100)
    origin = models.CharField(max_length=255)
    status = models.CharField(max_length=50, choices=AssetStatus.choices, default=AssetStatus.PENDING)
    appraise_status = models.CharField(max_length=50, choices=AssetAppraiseStatus.choices, default=AssetAppraiseStatus.NOT_APPRAISED)
    initial_price = models.DecimalField(max_digits=12, decimal_places=2)
    quantity = models.PositiveIntegerField(default=1)
    seller = models.ForeignKey(User, on_delete=models.CASCADE, related_name='assets_for_sale')
    winner = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, blank=True, related_name='won_assets')
    warehouse = models.ForeignKey('WareHouse', on_delete=models.SET_NULL, null=True, blank=True)
    appraiser = models.ForeignKey(Appraiser, on_delete=models.SET_NULL, null=True, blank=True, related_name='appraised_assets')
    appraised_value = models.DecimalField(max_digits=12, decimal_places=2, null=True, blank=True)
    appraisal_date = models.DateTimeField(null=True, blank=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name

class AssetMedia(models.Model):
    asset = models.ForeignKey(Asset, on_delete=models.CASCADE, related_name='media')
    media_type = models.CharField(max_length=20, choices=AssetMediaType.choices)
    file = models.FileField(upload_to="asset_media/")
    is_primary = models.BooleanField(default=False)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.asset.name} - {self.get_media_type_display()}"

class WareHouse(models.Model):
    name = models.CharField(max_length=255)
    address = models.TextField()
    capacity = models.PositiveIntegerField()
    current_occupancy = models.PositiveIntegerField(default=0)
    is_active = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name