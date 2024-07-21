<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;


return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * 
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id('id_user');
            $table->string('user_name');
            $table->string('last_name');
            $table->string('username')->unique();
            $table->date('date_of_birth');
            $table->string('phone_number');
            $table->string('another_phone_number')->nullable();
            $table->string('email')->unique();
            $table->string('password');
            $table->string('country');
            $table->string('city');
            $table->string('province');
            $table->string('state');
            $table->string('favourite');
            $table->string('status');
            $table->string('religion')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * 
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};