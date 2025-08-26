package com.diesgut.gof.behaviour_pattern.state_v1;

public interface IPaymentState {
    void paymentProcess();
    void changeState(Payment context);
}
