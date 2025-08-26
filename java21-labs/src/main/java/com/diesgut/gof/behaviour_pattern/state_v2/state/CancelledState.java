package com.diesgut.gof.behaviour_pattern.state_v2.state;

import com.diesgut.gof.behaviour_pattern.state_v2.context.CreditCard;

// Estado Concreto: Cancelled (Estado final)
public class CancelledState implements ICardState {
    // En este estado, ninguna operación es válida.
    private void operationNotAllowed() {
        System.out.println("Operación DENEGADA. La tarjeta está cancelada permanentemente.");
    }

    @Override
    public void makePurchase(CreditCard context, double amount) {
        operationNotAllowed();
    }

    @Override
    public void payBalance(CreditCard context, double amount) {
        operationNotAllowed();
    }

    @Override
    public void suspend(CreditCard context) {
        operationNotAllowed();
    }

    @Override
    public void activate(CreditCard context) {
        operationNotAllowed();
    }

    @Override
    public void cancel(CreditCard context) {
        operationNotAllowed();
    }
}
