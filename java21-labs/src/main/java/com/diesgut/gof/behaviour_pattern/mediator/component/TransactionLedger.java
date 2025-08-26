package com.diesgut.gof.behaviour_pattern.mediator.component;

public class TransactionLedger extends Component {
    public void record(String from, String to, double amount) {
        System.out.println("REGISTRADO: Transferencia de " + amount + " de " + from + " a " + to);
        // La transacción finaliza, notifica al mediador
        super.mediator.notify(this, "transactionRecorded");
    }
}
