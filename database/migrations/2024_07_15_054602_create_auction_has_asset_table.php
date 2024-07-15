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
        Schema::create('auction_has_asset', function (Blueprint $table) {
            $table->id();
            $table->foreignId('id_auction')->constrained('auctions');
            $table->foreignId('id_asset')->constrained('assets');
            $table->double('starting_price');
            $table->double('present_price');
            $table->text('auction_results')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('auction_has_asset');
    }
};
