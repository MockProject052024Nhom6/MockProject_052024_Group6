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
        Schema::create('tax_has_assets_has_auction', function (Blueprint $table) {
            $table->id();
            $table->foreignId('id_tax')->constrained('taxes');
            $table->foreignId('id_asset_has_auction')->constrained('auction_has_asset');
            $table->foreignId('id_bill')->constrained('bills');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('tax_has_assets_has_auction');
    }
};
