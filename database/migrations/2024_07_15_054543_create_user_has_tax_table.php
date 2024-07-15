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
        Schema::create('user_has_tax', function (Blueprint $table) {
            $table->foreignId('id_user')->constrained('users');
            $table->foreignId('id_tax')->constrained('taxes');
            $table->timestamp('date_of_application');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('user_has_tax');
    }
};
