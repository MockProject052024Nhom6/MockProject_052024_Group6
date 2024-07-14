from django.db import models
from users.models import User
from assets.models import Asset
from .enums import AuctionStatus, TypeAuction, UserHasAuctionStatus


class Auction(models.Model):
    auction_name = models.CharField(max_length=255)
    address = models.TextField()
    number_of_participants = models.PositiveIntegerField(default=0)
    start_time = models.DateTimeField()
    end_time = models.DateTimeField()
    type_auction = models.CharField(max_length=10, choices=TypeAuction.choices)
    status = models.BooleanField(default=True)
    id_auctioneer = models.ForeignKey(
        User, on_delete=models.CASCADE, related_name='auctions')
    status_auction = models.CharField(
        max_length=20, choices=AuctionStatus.choices, default=AuctionStatus.PENDING)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)
    id_holiday = models.ForeignKey(
        'Holiday', on_delete=models.SET_NULL, null=True, blank=True)


class UserHasAuction(models.Model):
    id_auction = models.ForeignKey(Auction, on_delete=models.CASCADE)
    id_user = models.ForeignKey(User, on_delete=models.CASCADE)
    deposits = models.DecimalField(max_digits=10, decimal_places=2)
    deposits_date = models.DateTimeField()
    status = models.BooleanField(default=True)
    user_has_auction_status = models.CharField(
        max_length=10, choices=UserHasAuctionStatus.choices)

    class Meta:
        unique_together = ('id_auction', 'id_user')


class Holiday(models.Model):
    holiday_date = models.DateField()
    holiday_name = models.CharField(max_length=255)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)


class AuctionHasAsset(models.Model):
    id_auction = models.ForeignKey(Auction, on_delete=models.CASCADE)
    id_asset = models.ForeignKey(Asset, on_delete=models.CASCADE)
    starting_price = models.DecimalField(max_digits=10, decimal_places=2)
    present_price = models.DecimalField(max_digits=10, decimal_places=2)
    auction_result = models.TextField(blank=True, null=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)


class Bid(models.Model):
    id_user = models.ForeignKey(User, on_delete=models.CASCADE)
    id_auction_has_asset = models.ForeignKey(
        AuctionHasAsset, on_delete=models.CASCADE)
    bid_amount = models.DecimalField(max_digits=10, decimal_places=2)
    bid_time = models.DateTimeField()
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)


class Fee(models.Model):
    id_auction_has_asset = models.ForeignKey(
        AuctionHasAsset, on_delete=models.CASCADE)
    fee_name = models.CharField(max_length=255)
    cost = models.DecimalField(max_digits=10, decimal_places=2)
    payment_status = models.BooleanField(default=False)
    payment_date = models.DateTimeField(null=True, blank=True)
    status = models.BooleanField(default=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)


class Contract(models.Model):
    id_auction_has_asset = models.ForeignKey(
        AuctionHasAsset, on_delete=models.CASCADE)
    contract_name = models.CharField(max_length=255)
    contract_date = models.DateTimeField()
    total_amount = models.DecimalField(max_digits=10, decimal_places=2)
    id_user = models.ForeignKey(User, on_delete=models.CASCADE)
    status_contract = models.CharField(max_length=20)
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
    id_tax = models.ForeignKey(Tax, on_delete=models.CASCADE)
    id_contract = models.ForeignKey(Contract, on_delete=models.CASCADE)
    tax_amount = models.DecimalField(max_digits=10, decimal_places=2)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)
