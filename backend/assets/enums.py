from django.db import models

class PropertyStatus(models.TextChoices):
    AVAILABLE = 'available', 'Available'
    SOLD = 'sold', 'Sold'
    RESERVED = 'reserved', 'Reserved'

class AssetStatus(models.TextChoices):
    ACTIVE = 'active', 'Active'
    INACTIVE = 'inactive', 'Inactive'
    PENDING = 'pending', 'Pending'
    SOLD = 'sold', 'Sold'

class AppraiserStatus(models.TextChoices):
    ACTIVE = 'active', 'Active'
    INACTIVE = 'inactive', 'Inactive'
    SUSPENDED = 'suspended', 'Suspended'


class AssetMediaType(models.TextChoices):
    IMAGE = "image", "Image"
    VIDEO = "video", "Video"
    DOCUMENT = "document", "Document"
