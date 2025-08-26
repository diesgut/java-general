package com.diesgut.gof.estructural_pattern.bridge.implementation.imp;

import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;

public class SmsNotificator implements INotificator {
    @Override
    public void sendMessage(String subject, String message) {
        // En SMS, el "asunto" puede ser ignorado o prefijado.
        System.out.println("Enviando por SMS...");
        System.out.println("Mensaje: " + subject + " - " + message);
        System.out.println("-------------------------");

    }
}
