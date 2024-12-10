<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EventController;
use App\Http\Controllers\TicketController;

Route::resource('events', EventController::class);
Route::resource('tickets', TicketController::class);


Route::get('/', function () {
    return view('welcome');
});
