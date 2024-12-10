<?php

namespace App\Http\Controllers;

use App\Models\Event;
use App\Models\Ticket;
use App\Notifications\TicketPurchasedNotification;
use Illuminate\Http\Request;

class TicketController extends Controller
{
    public function store(Request $request)
    {
        $ticket = new Ticket();
        $ticket->event_id = $request->event_id;
        $ticket->attendee_name = $request->attendee_name;
        $ticket->attendee_email = $request->attendee_email;
        $ticket->save();

        $event = Event::find($request->event_id);
        
        // Enviar notificación al usuario
        $user = auth()->user();
        $user->notify(new TicketPurchasedNotification($event));

        return redirect()->route('tickets.index');
    }
}

