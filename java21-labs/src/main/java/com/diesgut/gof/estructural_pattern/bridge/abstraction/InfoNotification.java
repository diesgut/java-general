package com.diesgut.gof.estructural_pattern.bridge.abstraction;

import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;

public class InfoNotification extends Notification {
    public InfoNotification(INotificator notificator) {
        super(notificator);
    }

    @Override
    public void notify(String mensaje) {
        String asunto = "Aviso de su Banco";
        super.notificator.sendMessage(asunto, mensaje);


    }
}
