<?php
namespace App\Http\Controllers;

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/sanpham', function(){
    return view('product');
});

Route::get('/login', function(){
    return view('Front_end.modules.auth.Login');
});

