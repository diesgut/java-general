package com.diesgut.gof.behaviour_pattern.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentApproved implements IPaymentState {
    @Override
    public void paymentProcess() {
        log.debug("El pago esta siendo aprobado");
    }

    @Override
    public void changeState(Payment context) {
        context.setState(new PaymentCompleted());
    }
}