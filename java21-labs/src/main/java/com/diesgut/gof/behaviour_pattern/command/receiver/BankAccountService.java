package com.diesgut.gof.behaviour_pattern.command.receiver;

// --- 2. El Receptor (Receiver) ---
// Es el mismo objeto de antes. El que sabe hacer el trabajo. El "Chef".
public class BankAccountService {
    private String id;
    private double balance;

    public BankAccountService(String id, double saldoInicial) {
        this.id = id;
        this.balance = saldoInicial;
        System.out.printf("Cuenta %s creada con saldo: $%.2f%n", id, balance);
    }

    public void deposit(double monto) {
        this.balance += monto;
        System.out.printf("Depósito de $%.2f en cuenta %s. Nuevo saldo: $%.2f%n", monto, id, balance);
    }

    public void withdrawal(double monto) {
        if (this.balance >= monto) {
            this.balance -= monto;
            System.out.printf("Retiro de $%.2f de cuenta %s. Nuevo saldo: $%.2f%n", monto, id, balance);
        } else {
            System.out.printf("Error: Saldo insuficiente para retirar $%.2f de la cuenta %s.%n", monto, id);
        }
    }
}
