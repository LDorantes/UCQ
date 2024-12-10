@extends('layouts.app')

@section('title', 'Tickets')

@section('content')
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1>Tickets</h1>
        <a href="{{ route('tickets.create') }}" class="btn btn-primary">Crear Ticket</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nombre del Asistente</th>
                <th>Email</th>
                <th>Evento</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($tickets as $ticket)
                <tr>
                    <td>{{ $ticket->attendee_name }}</td>
                    <td>{{ $ticket->attendee_email }}</td>
                    <td>{{ $ticket->event->name }}</td>
                    <td>
                        <a href="{{ route('tickets.show', $ticket->id) }}" class="btn btn-info btn-sm">Ver</a>
                        <a href="{{ route('tickets.edit', $ticket->id) }}" class="btn btn-warning btn-sm">Editar</a>
                        <form action="{{ route('tickets.destroy', $ticket->id) }}" method="POST" style="display:inline;">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </tbody>
    </table>
@endsection