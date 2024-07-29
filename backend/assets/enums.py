from django.db import models

class AssetAppraiseStatus(models.TextChoices):
    NOT_APPRAISED = 'not_appraised', 'Not appraised'
    APPRAISED = 'appraised', 'Appraised'

class AssetStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    IN_AUCTION = 'in_auction', 'In Auction'
    SOLD = 'sold', 'Sold'

class AppraiserStatus(models.TextChoices):
    ACTIVE = 'active', 'Active'
    INACTIVE = 'inactive', 'Inactive'

class AssetMediaType(models.TextChoices):
    IMAGE = "image", "Image"
    VIDEO = "video", "Video"
    DOCUMENT = "document", "Document"