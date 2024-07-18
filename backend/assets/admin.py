from django.contrib import admin
from .models import CategoryAsset, WareHouse, Appraiser, Asset, AssetMedia, InventoryTransaction

admin.site.register(CategoryAsset)
admin.site.register(WareHouse)
admin.site.register(Appraiser)
admin.site.register(Asset)
admin.site.register(AssetMedia)
admin.site.register(InventoryTransaction)