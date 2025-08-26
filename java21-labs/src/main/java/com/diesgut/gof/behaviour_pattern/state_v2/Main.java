package com.diesgut.gof.behaviour_pattern.state_v2;

import com.diesgut.gof.behaviour_pattern.state_v2.context.CreditCard;

public class Main {
    public static void main(String[] args) {
        CreditCard card = new CreditCard("1234-5678-9012-3456");

        System.out.println("--- Tarjeta Activa ---");
        card.makePurchase(100);
        card.payBalance(50);

        System.out.println("\n--- Suspendiendo Tarjeta ---");
        card.suspend();

        System.out.println("\n--- Tarjeta Suspendida ---");
        card.makePurchase(200); // Debería ser denegada
        card.payBalance(50);    // Debería ser aceptado

        System.out.println("\n--- Reactivando Tarjeta ---");
        card.activate();
        card.makePurchase(75); // Debería ser aprobada
    }
}
