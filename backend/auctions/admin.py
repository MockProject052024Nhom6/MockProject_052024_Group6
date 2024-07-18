from django.contrib import admin
from .models import Auction, UserHasAuction, Holiday, AuctionHasAsset, Bid, Fee, Contract, Tax, TaxHasContract

admin.site.register(Auction)
admin.site.register(UserHasAuction)
admin.site.register(Holiday)
admin.site.register(AuctionHasAsset)
admin.site.register(Bid)
admin.site.register(Fee)
admin.site.register(Contract)
admin.site.register(Tax)
admin.site.register(TaxHasContract)