package com.diesgut.gof.estructural_pattern.bridge.abstraction;

import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;

public class UrgentNotification extends Notification {
    public UrgentNotification(INotificator notificator) {
        super(notificator);
    }

    @Override
    public void notify(String mensaje) {
        String asunto = "[URGENTE] Alerta de Seguridad";
        String cuerpoMensaje = "ATENCIÓN: " + mensaje.toUpperCase() + ". Contacte al banco inmediatamente.";
        // Delega el envío al implementador que se le pasó.
        super.notificator.sendMessage(asunto, cuerpoMensaje);

    }
}
