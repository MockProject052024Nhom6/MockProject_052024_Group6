<?php
namespace App\Http\Controllers;

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('App');
});

Route::get('/login', function(){
    return view('Front_end.modules.auth.Login');
});

Route::get('/register', function(){
    return view('Front_end.modules.auth.Register');
});

Route::get('/forgot', function(){
    return view('Front_end.modules.auth.Forgot');
});

Route::get('/reset', function(){
    return view('Front_end.modules.auth.Reset');
});

Route::get('/profiler', function(){
    return view('Front_end.modules.pages.Profiler');
});
Route::get('/home', function(){
    return view('Front_end.modules.pages.Home');
});

