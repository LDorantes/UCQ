@extends('layouts.app')

@section('title', 'Eventos')

@section('content')
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1>Eventos</h1>
        <a href="{{ route('events.create') }}" class="btn btn-primary">Crear Evento</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Ubicación</th>
                <th>Fecha</th>
                <th>Capacidad</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($events as $event)
                <tr>
                    <td>{{ $event->name }}</td>
                    <td>{{ $event->description }}</td>
                    <td>{{ $event->location }}</td>
                    <td>{{ $event->date }}</td>
                    <td>{{ $event->capacity }}</td>
                    <td>{{ $event->price }}</td>
                    <td>
                        <a href="{{ route('events.show', $event->id) }}" class="btn btn-info btn-sm">Ver</a>
                        <a href="{{ route('events.edit', $event->id) }}" class="btn btn-warning btn-sm">Editar</a>
                        <form action="{{ route('events.destroy', $event->id) }}" method="POST" style="display:inline;">
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
