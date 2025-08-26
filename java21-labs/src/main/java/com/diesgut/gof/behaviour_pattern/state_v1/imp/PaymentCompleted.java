package com.diesgut.gof.behaviour_pattern.state_v1.imp;

import com.diesgut.gof.behaviour_pattern.state_v1.IPaymentState;
import com.diesgut.gof.behaviour_pattern.state_v1.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentCompleted implements IPaymentState {

    @Override
    public void paymentProcess() {
        log.debug("El pago esta siendo completado");
    }

    @Override
    public void changeState(Payment context) {
        log.debug("El pago fue aprobado");
    }
}
