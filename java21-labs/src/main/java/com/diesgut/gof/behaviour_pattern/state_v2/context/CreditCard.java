package com.diesgut.gof.behaviour_pattern.state_v2.context;

import com.diesgut.gof.behaviour_pattern.state_v2.state.ActiveState;
import com.diesgut.gof.behaviour_pattern.state_v2.state.ICardState;

// El Contexto
public class CreditCard {
    private String cardNumber;
    private double balance;
    private ICardState currentState; // Referencia al estado actual

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
        this.balance = 0;
        this.currentState = new ActiveState(); // El estado inicial
    }

    // El contexto permite a los estados cambiar el estado del contexto.
    public void setCurrentState(ICardState newState) {
        this.currentState = newState;
    }

    // Métodos para que los estados puedan modificar los datos internos del contexto, lo ideal es que no sea public
    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    // Los métodos del contexto simplemente delegan la llamada al objeto de estado.
    public void makePurchase(double amount) {
        currentState.makePurchase(this, amount);
    }

    public void payBalance(double amount) {
        currentState.payBalance(this, amount);
    }

    public void suspend() {
        currentState.suspend(this);
    }

    public void activate() {
        currentState.activate(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }
}
