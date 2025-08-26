package com.diesgut.gof.behaviour_pattern.state_v2.state;

import com.diesgut.gof.behaviour_pattern.state_v2.context.CreditCard;

// Estado Concreto: Suspended
public class SuspendedState implements ICardState {
    @Override
    public void makePurchase(CreditCard context, double amount) {
        System.out.println("Compra DENEGADA. La tarjeta está suspendida.");
    }
    @Override
    public void payBalance(CreditCard context, double amount) {
        System.out.println("Pago recibido por $" + amount);
        context.updateBalance(context.getBalance() - amount);
    }
    @Override
    public void suspend(CreditCard context) {
        System.out.println("La tarjeta ya está suspendida.");
    }
    @Override
    public void activate(CreditCard context) {
        System.out.println("Tarjeta reactivada. Transicionando a estado Activo.");
        context.setCurrentState(new ActiveState()); // Transición de estado
    }
    @Override
    public void cancel(CreditCard context) {
        System.out.println("Tarjeta cancelada desde estado suspendido.");
        context.setCurrentState(new CancelledState());
    }
}
