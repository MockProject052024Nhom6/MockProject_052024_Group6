from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import User

class CustomUserAdmin(UserAdmin):
    """Admin view for custom User model."""
    
    list_display = ("id", "email", "first_name", "last_name", "role")
    search_fields = ("id", "email")
    ordering = ("-created_date",)
    list_per_page = 10
    fieldsets = (
        (None, {'fields': ('email', 'password')}),
        ('Personal info', {'fields': ('first_name', 'last_name', 'date_of_birth', 'phone', 'another_phone', 'gender', 'country', 'city', 'state', 'avatar', 'credibility', 'identification_card')}),
        ('Permissions', {'fields': ('is_active', "role", 'groups', 'user_permissions')}),
        ('Important dates', {'fields': ('last_login',)}),
    )

admin.site.register(User, CustomUserAdmin)