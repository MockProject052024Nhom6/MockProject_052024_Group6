from django.urls import path, include
from rest_framework_simplejwt.views import TokenRefreshView
from rest_framework.routers import DefaultRouter
from .views import (
    signup, UserDetailView, change_password, login, logout, deactivate, 
    UserViewSet, NotificationViewSet, UserNotificationViewSet, 
    InformationAccountViewSet, TransactionHistoryViewSet
)

router = DefaultRouter()
router.register('users', UserViewSet, basename='user')
router.register('notifications', NotificationViewSet, basename='notification')
router.register('user-notifications', UserNotificationViewSet, basename='user-notification')
router.register('transaction-history', TransactionHistoryViewSet, basename='transaction-history')

urlpatterns = [
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('login/', login, name='login'),
    path('logout/', logout, name='logout'),
    path('signup/', signup, name='signup'),
    path('users/me/', UserDetailView.as_view(), name='user-detail'),
    path('change-password/', change_password, name='change-password'),
    path('users/me/deactivate/', deactivate, name='user-deactivate'),
    path('', include(router.urls)),
]