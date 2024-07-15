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
        Schema::create('auctions', function (Blueprint $table) {
            $table->id('id_auction');
            $table->string('auction_name');
            $table->integer('number_of_participants');
            $table->timestamp('start_time');
            $table->timestamp('end_time');
            $table->string('type_auction');
            $table->string('status');
            $table->string('host');
            $table->string('secretary');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('auctions');
    }
};