from django.urls import path
from rest_framework_simplejwt.views import TokenRefreshView
from .views import signup, UserDetailView, change_password, login, logout, deactivate

urlpatterns = [
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('login/', login, name='login'),
    path('logout/', logout, name='logout'),
    path('signup/', signup, name='signup'),
    path('user/', UserDetailView.as_view(), name='user-detail'),
    path('change-password/', change_password, name='change-password'),
    path('user/deactivate/', deactivate, name='user-deactivate'),
]