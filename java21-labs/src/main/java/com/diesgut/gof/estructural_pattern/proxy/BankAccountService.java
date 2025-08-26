package com.diesgut.gof.estructural_pattern.proxy;

// Servicio es una clase que proporciona una lógica de negocio útil.
//Esta es la clase que contiene la lógica de negocio principal y los datos sensibles.
public class BankAccountService implements IAccountService{
    private double balance;

    public BankAccountService(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.printf("[Real Account] Depósito de $%.2f realizado.\n", amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("[Real Account] Retiro de $%.2f realizado.\n", amount);
        } else {
            System.out.println("[Real Account] Fondos insuficientes.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
