package com.diesgut.poo;

// SavingsAccount.java

/**
 * Implementación concreta de una cuenta de ahorros.
 * Hereda de BankAccount y proporciona la lógica específica
 * para los métodos abstractos.
 */
public class SavingsAccount extends AbsctractBankAccount {

    private static final double MINIMUM_BALANCE_FOR_NO_FEE = 200.0;
    private static final double MAINTENANCE_FEE = 5.0;

    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Implementación del retiro para una cuenta de ahorros.
     * No permite retirar más dinero del que hay en el saldo (sin sobregiros).
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (this.getBalance() >= amount) {
            this.setBalance(this.getBalance() - amount);
            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + this.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal denied.");
        }
    }

    /**
     * Implementación de la comisión de mantenimiento.
     * Solo aplica una comisión si el saldo es inferior a un umbral mínimo.
     */
    @Override
    public void applyMonthlyMaintenance() {
        if (this.getBalance() < MINIMUM_BALANCE_FOR_NO_FEE) {
            this.setBalance(this.getBalance() - MAINTENANCE_FEE);
            System.out.println("Maintenance fee of $" + MAINTENANCE_FEE + " applied. New balance: $" + this.getBalance());
        } else {
            System.out.println("No maintenance fee applied for this savings account.");
        }
    }
}