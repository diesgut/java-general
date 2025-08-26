package com.diesgut.gof.behaviour_pattern.mediator.component;

// ----- COMPONENTES DESACOPLADOS (COLEGAS) -----
public class CustomerAccount extends Component {
    private double balance = 1000.00;
    private String accountId = "ACC-123";

    public boolean initiateTransfer(String toAccountId, double amount) {
        System.out.println("Iniciando solicitud de transferencia desde " + this.accountId);
        if (balance >= amount) {
            // Notifica al mediador que el saldo es suficiente
            System.out.println("Saldo verificado. Notificando al mediador...");
            super.mediator.notify(this, "balanceChecked");
            return true;
        } else {
            System.out.println("ERROR: Saldo insuficiente.");
            return false;
        }
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public String getAccountId() {
        return accountId;
    }
}
