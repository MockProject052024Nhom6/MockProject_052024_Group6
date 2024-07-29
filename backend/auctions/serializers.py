from rest_framework import serializers
from django.core.exceptions import ValidationError
from .models import Auction, AuctionParticipant, AuctionItem, Bid, Fee, Contract, Tax, ContractTax, TransactionHistory
from .enums import AuctionStatus
from assets.enums import AssetStatus

class AuctionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Auction
        fields = ['id', 'name', 'description', 'address', 'start_time', 'end_time', 'status', 'auctioneer', 
                  'starting_price', 'bid_increment', 'final_price', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']
        
    def validate(self, data):
        if data['end_time'] <= data['start_time']:
            raise serializers.ValidationError("End time must be after start time.")
        return data

class AuctionParticipantSerializer(serializers.ModelSerializer):
    class Meta:
        model = AuctionParticipant
        fields = ['id', 'auction', 'user', 'deposit', 'deposit_date', 'status', 'join_time']
        read_only_fields = ['id', 'join_time']

class AuctionItemSerializer(serializers.ModelSerializer):
    class Meta:
        model = AuctionItem
        fields = ['id', 'auction', 'asset', 'starting_price', 'current_price', 'final_price', 'status', 'bid_count', 
                  'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)
        if self.instance.status == AuctionStatus.FINISHED and self.instance.final_price:
            self.instance.asset.status = AssetStatus.SOLD
            self.instance.asset.save()

class BidSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bid
        fields = ['id', 'user', 'auction_item', 'amount', 'time', 'is_current_highest', 'status']
        read_only_fields = ['id', 'time']

    def validate_amount(self, value):
        auction_item_id = self.initial_data.get('auction_item')
        auction_item = AuctionItem.objects.get(id=auction_item_id)
        if auction_item.current_price >= value:
            raise serializers.ValidationError("Bid amount must be higher than current price.")
        return value

class FeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Fee
        fields = ['id', 'auction_item', 'name', 'fee_type', 'amount', 'payment_status', 'payment_date', 
                  'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class ContractSerializer(serializers.ModelSerializer):
    class Meta:
        model = Contract
        fields = ['id', 'auction_item', 'name', 'date', 'total_amount', 'winner', 'status', 'payment_status', 
                  'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class TaxSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tax
        fields = ['id', 'name', 'tax_type', 'rate', 'description', 'is_active', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class ContractTaxSerializer(serializers.ModelSerializer):
    class Meta:
        model = ContractTax
        fields = ['id', 'tax', 'contract', 'amount', 'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']

class TransactionHistorySerializer(serializers.ModelSerializer):
    auction = AuctionSerializer(read_only=True)
    auction_item = AuctionItemSerializer(read_only=True)

    class Meta:
        model = TransactionHistory
        fields = [
            'id', 'user', 'amount', 'transaction_type', 'status', 'description',
            'sender_account', 'sender_bank', 'sender_name', 'recipient_account',
            'recipient_bank', 'recipient_name', 'auction', 'auction_item',
            'note', 'transaction_date', 'created_date', 'modified_date'
        ]
        read_only_fields = ['id', 'created_date', 'modified_date']