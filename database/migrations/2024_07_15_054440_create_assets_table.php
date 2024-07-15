<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;


return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('assets', function (Blueprint $table) {
            $table->id('id_asset');
            $table->string('asset_name');
            $table->text('describe');
            $table->string('status');
            $table->string('size');
            $table->string('origin');
            $table->string('unit_range');
            $table->string('safety');
            $table->foreignId('id_user')->constrained('users');
            $table->foreignId('id_warehouse')->constrained('warehouses');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('assets');
    }
};
