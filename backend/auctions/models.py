from django.db import models
from django.core.exceptions import ValidationError
from time import timezone
from users.models import User
from assets.models import Asset
from .enums import AuctionStatus, TypeAuction, UserHasAuctionStatus
from assets.enums import PropertyStatus

class Auction(models.Model):
    auction_name = models.CharField(max_length=255)
    description = models.TextField(null=True)
    address = models.TextField()
    number_of_participants = models.PositiveIntegerField(default=0)
    start_time = models.DateTimeField()
    end_time = models.DateTimeField()
    type_auction = models.CharField(max_length=10, choices=TypeAuction.choices)
    status = models.BooleanField(default=True)
    id_auctioneer = models.ForeignKey(User, on_delete=models.CASCADE, related_name='auctions')
    status_auction = models.CharField(max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def clean(self):
        if self.end_time <= self.start_time:
            raise ValidationError("End time must be after start time.")

    def save(self, *args, **kwargs):
        self.full_clean()
        super().save(*args, **kwargs)

    def update_status(self):
        now = timezone.now()
        if self.start_time <= now < self.end_time:
            self.status_auction = AuctionStatus.ACTIVE
        elif now >= self.end_time:
            self.status_auction = AuctionStatus.FINISHED
        self.save()

class UserHasAuction(models.Model):
    id_auction = models.ForeignKey(Auction, on_delete=models.CASCADE, related_name='participants')
    id_user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='joined_auctions')
    deposits = models.DecimalField(max_digits=10, decimal_places=2)
    deposits_date = models.DateTimeField()
    status = models.BooleanField(default=True)
    user_has_auction_status = models.CharField(max_length=10, choices=UserHasAuctionStatus.choices)

    class Meta:
        unique_together = ('id_auction', 'id_user')

class Holiday(models.Model):
    holiday_date = models.DateField()
    holiday_name = models.CharField(max_length=255)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class AuctionHasAsset(models.Model):
    id_auction = models.ForeignKey(Auction, on_delete=models.CASCADE, related_name='auction_assets')
    id_asset = models.ForeignKey(Asset, on_delete=models.CASCADE, related_name='asset_auctions')
    starting_price = models.DecimalField(max_digits=10, decimal_places=2)
    current_price = models.DecimalField(max_digits=10, decimal_places=2)
    final_price = models.DecimalField(max_digits=10, decimal_places=2, null=True, blank=True)
    status = models.CharField(max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)
        if self.status == AuctionStatus.FINISHED and self.final_price:
            self.id_asset.property_status = PropertyStatus.SOLD
            self.id_asset.save()

class Bid(models.Model):
    id_user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='bids')
    id_auction_has_asset = models.ForeignKey(AuctionHasAsset, on_delete=models.CASCADE, related_name='bids')
    bid_amount = models.DecimalField(max_digits=10, decimal_places=2)
    bid_time = models.DateTimeField(auto_now_add=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def clean(self):
        if self.bid_amount <= self.id_auction_has_asset.current_price:
            raise ValidationError("Bid amount must be higher than current price.")

    def save(self, *args, **kwargs):
        self.full_clean()
        super().save(*args, **kwargs)

class Fee(models.Model):
    id_auction_has_asset = models.ForeignKey(AuctionHasAsset, on_delete=models.CASCADE, related_name='fees')
    fee_name = models.CharField(max_length=255)
    cost = models.DecimalField(max_digits=10, decimal_places=2)
    payment_status = models.BooleanField(default=False)
    payment_date = models.DateTimeField(null=True, blank=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class Contract(models.Model):
    id_auction_has_asset = models.ForeignKey(AuctionHasAsset, on_delete=models.CASCADE, related_name='contracts')
    contract_name = models.CharField(max_length=255)
    contract_date = models.DateTimeField()
    total_amount = models.DecimalField(max_digits=10, decimal_places=2)
    id_user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='contracts')
    status_contract = models.CharField(max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class Tax(models.Model):
    tax_name = models.CharField(max_length=255)
    tax_type = models.CharField(max_length=50)
    tax_rate = models.DecimalField(max_digits=5, decimal_places=2)
    description = models.TextField(blank=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

class TaxHasContract(models.Model):
    id_tax = models.ForeignKey(Tax, on_delete=models.CASCADE, related_name='contract_taxes')
    id_contract = models.ForeignKey(Contract, on_delete=models.CASCADE, related_name='taxes')
    tax_amount = models.DecimalField(max_digits=10, decimal_places=2)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)