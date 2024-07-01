<?php

use App\Http\Controllers\logicController;
use Illuminate\Support\Facades\Route;


// login page demo
Route::get('/', [logicController::class,'show'])->name('login');
