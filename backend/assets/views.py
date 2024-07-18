from rest_framework import viewsets, permissions, status
from rest_framework.response import Response
from users.permissions import IsAdminUser, IsStaffUser
from users.models import User