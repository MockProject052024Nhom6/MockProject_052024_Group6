from django.contrib import admin
from django.contrib.auth import get_user_model
from django.contrib.auth.admin import UserAdmin
from .forms import CustomUserCreationForm
from .models import Notification, UserNotification, InformationAccount, TransactionHistory
User = get_user_model()


class CustomUserAdmin(UserAdmin):
    add_form = CustomUserCreationForm
    model = User
    list_display = ("id", "email", "first_name",
                    "last_name", "role", "is_active")
    list_filter = ("role", "is_active", "created_date")
    fieldsets = (
        (None, {"fields": ("email", "password")}),
        ("Personal info", {"fields": ("first_name", "last_name", "date_of_birth", "phone", "another_phone",
         "gender", "country", "city", "state", "avatar", "credibility", "identification_card")}),
        ("Permissions", {"fields": ("is_active", "role", "groups", "user_permissions")}),
        ("Important dates", {"fields": ("last_login",)}),
    )
    add_fieldsets = (
        (None, {
            "classes": ("wide",),
            "fields": (
                "email", "first_name", "last_name", "password1", "password2", "role",
            )}
         ),
    )
    search_fields = ("email", "first_name", "last_name")
    ordering = ("-created_date",)
    readonly_fields = ("created_date",)
    list_per_page = 10


admin.site.register(User, CustomUserAdmin)
admin.site.register(Notification)
admin.site.register(UserNotification)
admin.site.register(TransactionHistory)
admin.site.register(InformationAccount)