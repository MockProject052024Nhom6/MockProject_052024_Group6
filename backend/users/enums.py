from django.db import models

class Gender(models.TextChoices):
    MALE = 'M', 'Male'
    FEMALE = 'F', 'Female'
    OTHER = 'O', 'Other'


class UserRole(models.TextChoices):
    USER = 'user', 'User'
    ADMIN = 'admin', 'Admin'
    STAFF = 'staff', 'Staff'