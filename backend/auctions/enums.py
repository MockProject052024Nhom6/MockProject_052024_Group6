from django.db import models

class AuctionStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    ACTIVE = 'active', 'Active'
    FINISHED = 'finished', 'Finished'
    CANCELLED = 'cancelled', 'Cancelled'
    
class AuctionStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    ACTIVE = 'active', 'Active'
    FINISHED = 'finished', 'Finished'
    CANCELLED = 'cancelled', 'Cancelled'

class AuctionParticipantStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    APPROVED = 'approved', 'Approved'
    REJECTED = 'rejected', 'Rejected'

class BidStatus(models.TextChoices):
    VALID = 'valid', 'Valid'
    INVALID = 'invalid', 'Invalid'
    OUTBID = 'outbid', 'Outbid'
    WINNING = 'winning', 'Winning'

class FeeType(models.TextChoices):
    REGISTRATION = 'registration', 'Registration'
    COMMISSION = 'commission', 'Commission'
    OTHER = 'other', 'Other'

class ContractStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    SIGNED = 'signed', 'Signed'
    COMPLETED = 'completed', 'Completed'
    CANCELLED = 'cancelled', 'Cancelled'

class PaymentStatus(models.TextChoices):
    UNPAID = 'unpaid', 'Unpaid'
    PARTIALLY_PAID = 'partially_paid', 'Partially Paid'
    PAID = 'paid', 'Paid'
    
class TransactionType(models.TextChoices):
    DEPOSIT = 'deposit', 'Deposit'
    WITHDRAWAL = 'withdrawal', 'Withdrawal'
    AUCTION_PAYMENT = 'auction_payment', 'Auction Payment'
    REFUND = 'refund', 'Refund'

class TransactionStatus(models.TextChoices):
    PENDING = 'pending', 'Pending'
    COMPLETED = 'completed', 'Completed'
    FAILED = 'failed', 'Failed'