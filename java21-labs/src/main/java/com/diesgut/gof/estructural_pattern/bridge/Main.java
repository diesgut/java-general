package com.diesgut.gof.estructural_pattern.bridge;

import com.diesgut.gof.estructural_pattern.bridge.abstraction.InfoNotification;
import com.diesgut.gof.estructural_pattern.bridge.abstraction.Notification;
import com.diesgut.gof.estructural_pattern.bridge.abstraction.UrgentNotification;
import com.diesgut.gof.estructural_pattern.bridge.implementation.INotificator;
import com.diesgut.gof.estructural_pattern.bridge.implementation.imp.MailNotificator;
import com.diesgut.gof.estructural_pattern.bridge.implementation.imp.SmsNotificator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // Creamos las implementaciones (los canales)
        INotificator byEmail = new MailNotificator();
        INotificator bySms = new SmsNotificator();

        // Ahora podemos combinar libremente

        // Escenario 1: Notificación urgente por SMS
        log.debug("== Escenario 1: Urgente por SMS ==");
        Notification urgentNotification = new UrgentNotification(bySms); //Interactuamos con la abstracción
        urgentNotification.notify("Se ha detectado un inicio de sesión sospechoso en su cuenta");

        // Escenario 2: Notificación informativa por Email
        log.debug("== Escenario 2: Informativa por Email ==");
        Notification infoNotification = new InfoNotification(byEmail); //Interactuamos con la abstracción
        infoNotification.notify("El mantenimiento programado se realizará este domingo a las 2am");
        // ¡Podemos cambiar la implementación en tiempo de ejecución si quisiéramos!
    }
}
