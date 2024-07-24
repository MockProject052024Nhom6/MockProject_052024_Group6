from celery import shared_task
from django.utils import timezone
from .models import Auction, AuctionHasAsset, AuctionStatus
from assets.models import Asset, PropertyStatus

@shared_task
def update_auction_statuses():
    now = timezone.now()
    
    auctions = Auction.objects.filter(status=True)
    for auction in auctions:
        auction.update_status()
    
    finished_auctions = Auction.objects.filter(status_auction=AuctionStatus.FINISHED, status=True)
    for auction in finished_auctions:
        auction_assets = AuctionHasAsset.objects.filter(id_auction=auction)
        for auction_asset in auction_assets:
            if auction_asset.status != AuctionStatus.FINISHED:
                auction_asset.status = AuctionStatus.FINISHED
                highest_bid = auction_asset.bids.order_by('-bid_amount').first()
                if highest_bid:
                    auction_asset.final_price = highest_bid.bid_amount
                    asset = auction_asset.id_asset
                    asset.id_user_winner = highest_bid.id_user
                    asset.property_status = PropertyStatus.SOLD
                    asset.save()
                auction_asset.save()