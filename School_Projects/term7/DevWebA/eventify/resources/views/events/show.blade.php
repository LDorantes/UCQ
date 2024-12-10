@extends('layouts.app')

@section('title', 'Detalles del Evento')

@section('content')
    <h1>{{ $event->name }}</h1>
    <p>{{ $event->description }}</p>
    <p><strong>Ubicación:</strong> {{ $event->location }}</p>
    <p><strong>Fecha:</strong> {{ $event->date }}</p>
    <p><strong>Capacidad:</strong> {{ $event->capacity }}</p>
    <p><strong>Precio:</strong> ${{ $event->price }}</p>
    <a href="{{ route('events.index') }}" class="btn btn-secondary">Volver a la lista</a>
@endsection
