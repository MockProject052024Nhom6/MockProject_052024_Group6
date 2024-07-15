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
        Schema::create('transaction_histories', function (Blueprint $table) {
            $table->id('id_transaction_history');
            $table->foreignId('id_user')->constrained('users');
            $table->double('transaction_amount');
            $table->string('sender');
            $table->string('sender_account_number');
            $table->string('bank');
            $table->string('account_owner_name');
            $table->string('receiver');
            $table->string('receiver_account_number');
            $table->string('status');
            $table->text('note')->nullable();
            $table->string('day_trading');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('transaction_histories');
    }
};
