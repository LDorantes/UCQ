<?php

namespace App\Http\Controllers;

use App\Models\Ticket;
use Illuminate\Http\Request;

class TicketController extends Controller
{
    public function index()
    {
        $tickets = Ticket::all();
        return view('tickets.index', compact('tickets'));
    }

    public function create()
    {
        return view('tickets.create');
    }

    public function store(Request $request)
    {
        $ticket = new Ticket();
        $ticket->event_id = $request->event_id;
        $ticket->attendee_name = $request->attendee_name;
        $ticket->attendee_email = $request->attendee_email;
        $ticket->save();

        return redirect()->route('tickets.index');
    }

    public function show($id)
    {
        $ticket = Ticket::find($id);
        return view('tickets.show', compact('ticket'));
    }

    public function edit($id)
    {
        $ticket = Ticket::find($id);
        return view('tickets.edit', compact('ticket'));
    }

    public function update(Request $request, $id)
    {
        $ticket = Ticket::find($id);
        $ticket->attendee_name = $request->attendee_name;
        $ticket->attendee_email = $request->attendee_email;
        $ticket->save();

        return redirect()->route('tickets.index');
    }

    public function destroy($id)
    {
        Ticket::find($id)->delete();
        return redirect()->route('tickets.index');
    }
}
