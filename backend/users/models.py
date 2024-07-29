from django.db import models
from django.contrib.auth.models import AbstractUser, BaseUserManager
from django.core.validators import MinValueValidator, MaxValueValidator
from .enums import Gender, UserRole

class CustomUserManager(BaseUserManager):
    """Custom user manager."""

    def create_user(self, email, password=None, **extra_fields):
        if not email:
            raise ValueError('The Email field must be set')
        email = self.normalize_email(email)
        extra_fields.setdefault('role', UserRole.USER)
        user = self.model(email=email, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)
        extra_fields.setdefault('role', UserRole.ADMIN)

        if extra_fields.get('is_staff') is not True:
            raise ValueError('Superuser must have is_staff=True.')
        if extra_fields.get('is_superuser') is not True:
            raise ValueError('Superuser must have is_superuser=True.')

        return self.create_user(email, password, **extra_fields)


class User(AbstractUser):
    """Custom user model."""

    username = None
    date_joined = None

    email = models.EmailField('Email address', unique=True)
    first_name = models.CharField("First name", max_length=150)
    last_name = models.CharField("Last name", max_length=150)
    role = models.CharField(
        max_length=10, choices=UserRole.choices, default=UserRole.USER)
    date_of_birth = models.DateField(blank=True, null=True)
    phone = models.CharField(max_length=20, blank=True)
    another_phone = models.CharField(max_length=20, blank=True)
    gender = models.CharField(max_length=1, choices=Gender.choices, blank=True)
    country = models.CharField(max_length=100, blank=True)
    city = models.CharField(max_length=100, blank=True)
    state = models.CharField(max_length=100, blank=True)
    avatar = models.ImageField(upload_to='avatars/', blank=True)
    credibility = models.IntegerField(
        default=0, validators=[MinValueValidator(0), MaxValueValidator(100)])
    identification_card = models.CharField(max_length=50, blank=True)
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    objects = CustomUserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    def save(self, *args, **kwargs):
        """Save user, setting permissions based on role."""

        if self.role == UserRole.ADMIN:
            self.is_superuser = True
            self.is_staff = True
        elif self.role == UserRole.STAFF:
            self.is_superuser = False
            self.is_staff = True
        else:
            self.is_superuser = False
            self.is_staff = False
        super().save(*args, **kwargs)

    def __str__(self):
        return self.email


class Notification(models.Model):
    title = models.CharField(max_length=255)
    content = models.TextField()
    created_date = models.DateTimeField(auto_now_add=True)
    modified_date = models.DateTimeField(auto_now=True)

    users = models.ManyToManyField(
        User, through='UserNotification', related_name='notifications')

    def __str__(self):
        return self.title


class UserNotification(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    notification = models.ForeignKey(Notification, on_delete=models.CASCADE)
    is_read = models.BooleanField(default=False)
    read_date = models.DateTimeField(null=True, blank=True)

    class Meta:
        unique_together = ('user', 'notification')

    def __str__(self):
        return f"{self.user.email} - {self.notification.title}"
