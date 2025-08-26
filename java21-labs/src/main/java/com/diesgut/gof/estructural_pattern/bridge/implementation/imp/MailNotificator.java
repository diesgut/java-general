package com.diesgut.gof.estructural_pattern.bridge.implementation.imp;

import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;

public class MailNotificator implements INotificator {
    @Override
    public void sendMessage(String subject, String message) {
        System.out.println("Enviando por EMAIL...");
        System.out.println("Asunto: " + subject);
        System.out.println("Cuerpo: " + message);
        System.out.println("-------------------------");
    }
}
