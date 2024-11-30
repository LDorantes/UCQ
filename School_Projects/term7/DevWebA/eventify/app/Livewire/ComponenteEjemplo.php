<?php

namespace App\Livewire;

use Livewire\Component;

class ComponenteEjemplo extends Component
{
    public function render()
    {
        return view('livewire.componente-ejemplo');
    }
    public $mensaje = "¡Hola desde Livewire!";

    
}
