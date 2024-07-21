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
            $table->unsignedBigInteger('id_user');
            $table->unsignedBigInteger('id_warehouse');
            $table->timestamps();

            // Thêm khóa ngoại
            $table->foreign('id_user')
                ->references('id_user')
                ->on('users')
                ->onDelete('cascade');

            $table->foreign('id_warehouse')
                ->references('id_warehouse')
                ->on('warehouses')
                ->onDelete('cascade');
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
