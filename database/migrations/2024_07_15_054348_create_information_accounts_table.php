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
        Schema::create('information_accounts', function (Blueprint $table) {
            $table->id('id_user');
            $table->string('cit_number');
            $table->string('expiration');
            $table->string('cvy');
            $table->string('billing_address');
            $table->string('posted_code');
            $table->string('first_name');
            $table->string('last_name');
            $table->string('city_account');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('information_accounts');
    }
};
