<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EventController;
use App\Http\Controllers\TicketController;

Route::resource('events', EventController::class);
Route::resource('tickets', TicketController::class);

 // Rutas para la página principal 
 Route::get('/', function () { return view('welcome'); }); 
 // Agrupamos las rutas que requieren autenticación
  Route::middleware(['auth'])->group(function () { 
 // Rutas para gestión de eventos 
 Route::resource('events', EventController::class); 
 // Rutas para gestión de tickets 
 Route::resource('tickets', TicketController::class); }); 
 // Rutas de autenticación generadas por Laravel Breeze (u otro paquete de autenticación) 
 require __DIR__.'/auth.php';
