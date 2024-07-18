# Generated by Django 5.0.6 on 2024-07-14 15:39

import django.db.models.deletion
from django.conf import settings
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='CategoryAsset',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=255)),
            ],
        ),
        migrations.CreateModel(
            name='WareHouse',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('warehouse_name', models.CharField(max_length=255)),
                ('address_warehouse', models.TextField()),
                ('asset_placement', models.TextField()),
                ('status', models.BooleanField(default=True)),
                ('created_date', models.DateTimeField(auto_now_add=True)),
                ('modified_date', models.DateTimeField(auto_now=True)),
            ],
        ),
        migrations.CreateModel(
            name='Appraiser',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('experiences', models.TextField()),
                ('specialized', models.CharField(max_length=255)),
                ('status_appraiser', models.CharField(max_length=50)),
                ('status', models.BooleanField(default=True)),
                ('created_date', models.DateTimeField(auto_now_add=True)),
                ('modified_date', models.DateTimeField(auto_now=True)),
                ('id_user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='Asset',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('asset_name', models.CharField(max_length=255)),
                ('description', models.TextField()),
                ('status', models.BooleanField(default=True)),
                ('size', models.CharField(max_length=100)),
                ('origin', models.CharField(max_length=255)),
                ('property_status', models.CharField(max_length=50)),
                ('quantity', models.PositiveIntegerField()),
                ('status_asset', models.CharField(max_length=50)),
                ('created_date', models.DateTimeField(auto_now_add=True)),
                ('modified_date', models.DateTimeField(auto_now=True)),
                ('id_appraiser', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='appraiser_assets', to='assets.appraiser')),
                ('id_seller', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='sold_assets', to=settings.AUTH_USER_MODEL)),
                ('id_user_winner', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='won_assets', to=settings.AUTH_USER_MODEL)),
                ('id_category', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='assets.categoryasset')),
                ('id_warehouse', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='assets.warehouse')),
            ],
        ),
        migrations.CreateModel(
            name='AssetMedia',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('link', models.URLField()),
                ('type', models.CharField(max_length=50)),
                ('description', models.TextField()),
                ('status', models.BooleanField(default=True)),
                ('created_date', models.DateTimeField(auto_now_add=True)),
                ('modified_date', models.DateTimeField(auto_now=True)),
                ('id_asset', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='assets.asset')),
            ],
        ),
        migrations.CreateModel(
            name='InventoryTransaction',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('import_date', models.DateTimeField(blank=True, null=True)),
                ('export_date', models.DateTimeField(blank=True, null=True)),
                ('description', models.TextField()),
                ('status', models.BooleanField(default=True)),
                ('created_date', models.DateTimeField(auto_now_add=True)),
                ('modified_date', models.DateTimeField(auto_now=True)),
                ('id_warehouse', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='assets.warehouse')),
            ],
        ),
    ]