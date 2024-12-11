<?php

use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Auth\RegisterController;
use App\Http\Controllers\Auth\ForgotPasswordController;
use App\Http\Controllers\Auth\ResetPasswordController;
use App\Http\Controllers\EventController;
use App\Http\Controllers\TicketController;

// Rutas para la página principal
Route::get('/', function () {
    return view('welcome');
});

// Rutas de autenticación
Route::get('login', [LoginController::class, 'showLoginForm'])->name('login');
Route::post('login', [LoginController::class, 'login']);
Route::post('logout', [LoginController::class, 'logout'])->name('logout');

Route::get('register', [RegisterUserController::class, 'showRegistrationForm'])->name('register');
Route::post('register', [RegisterUserController::class, 'register']);

Route::get('password/reset', [NewPasswordController::class, 'showLinkRequestForm'])->name('password.request');
Route::post('password/email', [NewPasswordController::class, 'sendResetLinkEmail'])->name('password.email');
Route::get('password/reset/{token}', [PasswordResetLinkController::class, 'showResetForm'])->name('password.reset');
Route::post('password/reset', [PasswordResetLinkController::class, 'reset']);

// Agrupamos las rutas que requieren autenticación
Route::middleware(['auth'])->group(function () {
    // Rutas para gestión de eventos
    Route::resource('events', EventController::class);

    // Rutas para gestión de tickets
    Route::resource('tickets', TicketController::class);
});


Route::get('/', function () {
    return view('welcome');
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

require __DIR__.'/auth.php';
