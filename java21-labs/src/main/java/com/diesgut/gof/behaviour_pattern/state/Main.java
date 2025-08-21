package com.diesgut.gof.behaviour_pattern.state;

public class Main {
    public static void main(String[] args) {
        Payment pago = new Payment(); // initial state is pending
        pago.paymentProcessing(); //here state is PaymentPending

        pago.changeState(); //here state is changed to ProcessingPayment
        pago.paymentProcessing(); //here state is ProcessingPayment

        pago.changeState(); //here state is changed to PaymentApproved
        pago.paymentProcessing(); //here state is PaymentApproved

        pago.changeState(); //here state is changed to PaymentCompleted
        pago.paymentProcessing(); //here state is PaymentCompleted

        pago.changeState(); //this method will don't state change
    }
}
