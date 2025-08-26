package com.diesgut.gof.behaviour_pattern.state_v2.state;

import com.diesgut.gof.behaviour_pattern.state_v2.context.CreditCard;

public class ActiveState implements ICardState {
    @Override
    public void makePurchase(CreditCard context, double amount) {
        System.out.println("Compra APROBADA por $" + amount);
        context.updateBalance(context.getBalance() + amount);
    }
    @Override
    public void payBalance(CreditCard context, double amount) {
        System.out.println("Pago recibido por $" + amount);
        context.updateBalance(context.getBalance() - amount);
    }
    @Override
    public void suspend(CreditCard context) {
        System.out.println("Tarjeta suspendida. Transicionando a estado Suspendido.");
        context.setCurrentState(new SuspendedState()); // Transición de estado
    }
    @Override
    public void activate(CreditCard context) {
        System.out.println("La tarjeta ya está activa.");
    }
    @Override
    public void cancel(CreditCard context) {
        System.out.println("Tarjeta cancelada. Transicionando a estado Cancelado.");
        context.setCurrentState(new CancelledState()); // Transición de estado
    }
}
