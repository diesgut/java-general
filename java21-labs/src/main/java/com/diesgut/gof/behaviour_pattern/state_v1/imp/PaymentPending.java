package com.diesgut.gof.behaviour_pattern.state_v1.imp;

import com.diesgut.gof.behaviour_pattern.state_v1.IPaymentState;
import com.diesgut.gof.behaviour_pattern.state_v1.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentPending implements IPaymentState {
    @Override
    public void paymentProcess() {
        log.debug("El pago inicia con el estado pendiente");
    }

    @Override
    public void changeState(Payment context) {
        context.setState(new ProcessingPayment());
    }
}
