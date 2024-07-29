from django.db import models
from django.core.exceptions import ValidationError
from django.utils import timezone
from users.models import User
from assets.models import Asset
from assets.enums import AssetStatus
from .enums import AuctionStatus, AuctionParticipantStatus, BidStatus, FeeType, ContractStatus, PaymentStatus, TransactionStatus, TransactionType

class Auction(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField(null=True, blank=True)
    address = models.TextField()
    start_time = models.DateTimeField()
    end_time = models.DateTimeField()
    status = models.CharField(max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    auctioneer = models.ForeignKey(User, on_delete=models.CASCADE, related_name='organized_auctions')
    starting_price = models.DecimalField(max_digits=12, decimal_places=2)
    bid_increment = models.DecimalField(max_digits=10, decimal_places=2)
    final_price = models.DecimalField(max_digits=12, decimal_places=2, null=True, blank=True)
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
            self.status = AuctionStatus.ACTIVE
        elif now >= self.end_time:
            self.status = AuctionStatus.FINISHED
        self.save()

    def __str__(self):
        return self.name

class AuctionParticipant(models.Model):
    auction = models.ForeignKey(Auction, on_delete=models.CASCADE, related_name='participants')
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='joined_auctions')
    deposit = models.DecimalField(max_digits=12, decimal_places=2)
    deposit_date = models.DateTimeField()
    status = models.CharField(max_length=10, choices=AuctionParticipantStatus.choices)
    join_time = models.DateTimeField(auto_now_add=True)

    class Meta:
        unique_together = ('auction', 'user')

    def __str__(self):
        return f"{self.user.username} in {self.auction.name}"

class AuctionItem(models.Model):
    auction = models.ForeignKey(Auction, on_delete=models.CASCADE, related_name='items')
    asset = models.ForeignKey(Asset, on_delete=models.CASCADE, related_name='auction_entries')
    starting_price = models.DecimalField(max_digits=12, decimal_places=2)
    current_price = models.DecimalField(max_digits=12, decimal_places=2)
    final_price = models.DecimalField(max_digits=12, decimal_places=2, null=True, blank=True)
    status = models.CharField(max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    bid_count = models.PositiveIntegerField(default=0)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.asset.name} in {self.auction.name}"

    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)
        if self.status == AuctionStatus.FINISHED and self.final_price:
            self.asset.status = AssetStatus.SOLD
            self.asset.save()

class Bid(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='bids')
    auction_item = models.ForeignKey(AuctionItem, on_delete=models.CASCADE, related_name='bids')
    amount = models.DecimalField(max_digits=12, decimal_places=2)
    time = models.DateTimeField(auto_now_add=True)
    is_current_highest = models.BooleanField(default=False)
    status = models.CharField(max_length=20, choices=BidStatus.choices, default=BidStatus.VALID)

    def clean(self):
        if self.amount <= self.auction_item.current_price:
            raise ValidationError("Bid amount must be higher than current price.")

    def save(self, *args, **kwargs):
        self.full_clean()
        super().save(*args, **kwargs)

    def __str__(self):
        return f"Bid of {self.amount} by {self.user.username} for {self.auction_item.asset.name}"

class Fee(models.Model):
    auction_item = models.ForeignKey(AuctionItem, on_delete=models.CASCADE, related_name='fees')
    name = models.CharField(max_length=255)
    fee_type = models.CharField(max_length=50, choices=FeeType.choices)
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    payment_status = models.BooleanField(default=False)
    payment_date = models.DateTimeField(null=True, blank=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.name} for {self.auction_item.asset.name}"

class Contract(models.Model):
    auction_item = models.OneToOneField(AuctionItem, on_delete=models.CASCADE, related_name='contract')
    name = models.CharField(max_length=255)
    date = models.DateTimeField()
    total_amount = models.DecimalField(max_digits=12, decimal_places=2)
    winner = models.ForeignKey(User, on_delete=models.CASCADE, related_name='won_contracts')
    status = models.CharField(max_length=20, choices=ContractStatus.choices, default=ContractStatus.PENDING)
    payment_status = models.CharField(max_length=20, choices=PaymentStatus.choices, default=PaymentStatus.UNPAID)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"Contract for {self.auction_item.asset.name}"

class Tax(models.Model):
    name = models.CharField(max_length=255)
    tax_type = models.CharField(max_length=50)
    rate = models.DecimalField(max_digits=5, decimal_places=2)
    description = models.TextField(blank=True)
    is_active = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name

class ContractTax(models.Model):
    tax = models.ForeignKey(Tax, on_delete=models.CASCADE, related_name='contract_taxes')
    contract = models.ForeignKey(Contract, on_delete=models.CASCADE, related_name='taxes')
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.tax.name} for {self.contract.name}"
    
    
class TransactionHistory(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='transactions')
    amount = models.DecimalField(max_digits=12, decimal_places=2)
    transaction_type = models.CharField(max_length=20, choices=TransactionType.choices)
    status = models.CharField(max_length=20, choices=TransactionStatus.choices, default=TransactionStatus.PENDING)
    description = models.TextField()
    sender_account = models.CharField(max_length=50)
    sender_bank = models.CharField(max_length=100)
    sender_name = models.CharField(max_length=100)
    recipient_account = models.CharField(max_length=50)
    recipient_bank = models.CharField(max_length=100, blank=True)
    recipient_name = models.CharField(max_length=100)
    auction = models.ForeignKey(Auction, on_delete=models.SET_NULL, null=True, blank=True, related_name='transactions')
    auction_item = models.ForeignKey(AuctionItem, on_delete=models.SET_NULL, null=True, blank=True, related_name='transactions')
    note = models.TextField(blank=True)
    transaction_date = models.DateTimeField()
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.transaction_type} - {self.amount} - {self.user.email}"