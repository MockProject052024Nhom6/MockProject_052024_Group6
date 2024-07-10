from django.db import models
from django.contrib.auth.models import AbstractUser, BaseUserManager
from django.core.validators import MinValueValidator, MaxValueValidator


class User(AbstractUser):
    date_of_birth = models.DateField(blank=True, null=True)
    phone = models.CharField(max_length=20, blank=True)
    another_phone = models.CharField(max_length=20, blank=True)
    GENDER_CHOICES = [
        ('M', 'Male'),
        ('F', 'Female'),
        ('O', 'Other'),
    ]
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES, blank=True)
    country = models.CharField(max_length=100, blank=True)
    city = models.CharField(max_length=100, blank=True)
    state = models.CharField(max_length=100, blank=True)
    avatar = models.ImageField(upload_to='avatars/', blank=True)
    credibility = models.IntegerField(default=0, validators=[MinValueValidator(0), MaxValueValidator(100)])
    identification_card = models.CharField(max_length=50, blank=True)
    created_date = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.username
