package com.diesgut.gof.behaviour_pattern.state;

public interface IPaymentState {
    void paymentProcess();
    void changeState(Payment context);
}
