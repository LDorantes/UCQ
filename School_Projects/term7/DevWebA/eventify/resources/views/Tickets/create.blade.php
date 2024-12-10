@extends('layouts.app')

@section('title', 'Crear Ticket')

@section('content')
    <h1>Crear Ticket</h1>
    <form action="{{ route('tickets.store') }}" method="POST">
        @csrf
        <div class="mb-3">
            <label for="event_id" class="form-label">Evento</label>
            <select class="form-control" id="event_id" name="event_id" required>
                @foreach ($events as $event)
                    <option value="{{ $event->id }}">{{ $event->name }}</option>
                @endforeach
            </select>
        </div>
        <div class="mb-3">
            <label for="attendee_name" class="form-label">Nombre del Asistente</label>
            <input type="text" class="form-control" id="attendee_name" name="attendee_name" required>
        </div>
        <div class="mb-3">
            <label for="attendee_email" class="form-label">Email</label>
            <input type="email" class="form-control" id="attendee_email" name="attendee_email" required>
        </div>
        <button type="submit" class="btn btn-primary">Crear Ticket</button>
    </form>
@endsection