from django.db import models

class AuctionStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    ACTIVE = 'active', 'Active'
    FINISHED = 'finished', 'Finished'
    CANCELLED = 'cancelled', 'Cancelled'

class UserHasAuctionStatus(models.TextChoices):
    JOIN = 'join', 'Join'
    CANCEL = 'cancel', 'Cancel'