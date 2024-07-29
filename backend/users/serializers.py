from rest_framework import serializers
from django.contrib.auth.password_validation import validate_password
from django.core.exceptions import ValidationError
from .models import User, Notification, UserNotification


class AdminUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'


class StaffUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['first_name', 'last_name', 'phone', 'another_phone',  'identification_card',
                  'gender', 'country', 'city', 'state', 'avatar', 'created_date', 'modified_date']
        read_only_fields = ['email', 'role', 'created_date', 'modified_date']


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = [
            'id', 'email', 'first_name', 'last_name', 'role', 'date_of_birth', 'phone',
            'another_phone', 'gender', 'country', 'city', 'state', 'avatar',
            'credibility', 'identification_card', 'created_date', 'modified_date'
        ]
        read_only_fields = ['id', 'role', 'credibility',
                            'created_date', 'modified_date']


class SignUpSerializer(serializers.ModelSerializer):
    confirm_password = serializers.CharField(write_only=True, required=True)

    class Meta:
        model = User
        fields = ['email', 'first_name', 'last_name',
                  'password', 'confirm_password']
        extra_kwargs = {
            'email': {'required': True},
            'first_name': {'required': True},
            'last_name': {'required': True},
            'password': {'write_only': True, 'required': True, 'validators': [validate_password]}
        }

    def validate(self, attrs):
        if attrs['password'] != attrs['confirm_password']:
            raise serializers.ValidationError(
                {"message": "Password fields didn't match."})
        return attrs

    def create(self, validated_data):
        validated_data.pop('confirm_password')
        user = User.objects.create_user(**validated_data)
        return user


class ChangePasswordSerializer(serializers.Serializer):
    old_password = serializers.CharField(required=True, write_only=True)
    new_password = serializers.CharField(required=True, write_only=True)
    confirm_new_password = serializers.CharField(
        required=True, write_only=True)

    def validate(self, attrs):
        if attrs['new_password'] != attrs['confirm_new_password']:
            raise serializers.ValidationError(
                {"confirm_new_password": "New passwords do not match."})

        self.validate_old_password(attrs['old_password'])
        self.validate_new_password(attrs['new_password'])

        return attrs

    def validate_old_password(self, value):
        user = self.context['request'].user
        if not user.check_password(value):
            raise serializers.ValidationError("Old password is incorrect")
        return value

    def validate_new_password(self, value):
        user = self.context['request'].user
        try:
            validate_password(value, user)
        except ValidationError as e:
            raise serializers.ValidationError(list(e.messages))

        if value == self.initial_data.get('old_password'):
            raise serializers.ValidationError(
                "New password should be different from the old password.")

        return value

    def save(self):
        user = self.context['request'].user
        new_password = self.validated_data['new_password']
        user.set_password(new_password)
        user.save()
        return user


class NotificationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Notification
        fields = ['id', 'title', 'content',
                  'created_date', 'modified_date']
        read_only_fields = ['id', 'created_date', 'modified_date']


class UserNotificationSerializer(serializers.ModelSerializer):
    notification = NotificationSerializer(read_only=True)

    class Meta:
        model = UserNotification
        fields = ['id', 'notification', 'is_read', 'read_date']