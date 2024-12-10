@extends('layouts.app')

@section('title', 'Detalles del Ticket')

@section('content')
    <h1>Detalles del Ticket</h1>
    <p><strong>Evento:</strong> {{ $ticket->event->name }}</p>
    <p><strong>Nombre del Asistente:</strong> {{ $ticket->attendee_name }}</p>
    <p><strong>Email:</strong> {{ $ticket->attendee_email }}</p>
    <a href="{{ route('tickets.index') }}" class="btn btn-secondary">Volver a la lista</a>
@endsection