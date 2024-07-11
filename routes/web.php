<?php

use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;


// login page demo
Route::get('/', [UserController::class,'show'])->name('login');
