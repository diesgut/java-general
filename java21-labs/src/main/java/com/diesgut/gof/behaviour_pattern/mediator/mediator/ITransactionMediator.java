package com.diesgut.gof.behaviour_pattern.mediator.mediator;

import com.diesgut.gof.behaviour_pattern.mediator.component.Component;

// Interfaz para nuestro Mediador
public interface ITransactionMediator {
    // Los componentes notificarán al mediador a través de este método
    void notify(Component component, String event);
}
