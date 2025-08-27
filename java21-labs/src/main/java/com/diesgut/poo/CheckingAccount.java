package com.diesgut.poo;

/**
 * Implementación concreta de una cuenta corriente.
 * Permite sobregiros hasta un límite definido.
 */
public class CheckingAccount extends AbsctractBankAccount {

    private double overdraftLimit;
    private static final double MONTHLY_FEE = 10.0;

    public CheckingAccount(String accountNumber, double overdraftLimit) {
        super(accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Implementación del retiro para una cuenta corriente.
     * Permite que el saldo se vuelva negativo hasta el límite de sobregiro.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        // El retiro es válido si la cantidad es menor o igual al saldo más el límite de sobregiro.
        if (this.getBalance() + this.overdraftLimit >= amount) {
            this.setBalance(this.getBalance() - amount);
            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + this.getBalance());
        } else {
            System.out.println("Exceeds overdraft limit. Withdrawal denied.");
        }
    }

    /**
     * Implementación de la comisión de mantenimiento.
     * Aplica una comisión mensual fija, sin importar el saldo.
     */
    @Override
    public void applyMonthlyMaintenance() {
        this.setBalance(this.getBalance() - MONTHLY_FEE);
        System.out.println("Monthly fee of $" + MONTHLY_FEE + " applied. New balance: $" + this.getBalance());
    }
}