package com.diesgut.gof.behaviour_pattern.state;

public class Payment {
    private IPaymentState paymentState;

    public Payment() {
        this.paymentState = new PaymentPending();  // initial state
    }

    public void setState(IPaymentState estado) {
        this.paymentState = estado;
    }

    public void paymentProcessing() {
        paymentState.paymentProcess();
    }

    public void changeState() {
        paymentState.changeState(this);
    }
}
