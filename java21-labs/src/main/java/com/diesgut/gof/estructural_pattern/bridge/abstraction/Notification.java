package com.diesgut.gof.estructural_pattern.bridge.abstraction;

import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;

public abstract class Notification {
    // Este es el PUENTE (BRIDGE)!, se usa composición.
    protected INotificator notificator;

    public Notification(INotificator notificator) {
        this.notificator = notificator;
    }

    // Método de alto nivel que las subclases deben implementar.
    public abstract void notify(String mensaje);
}
