package com.diesgut.gof.behaviour_pattern.observer.subjects;

import com.diesgut.gof.behaviour_pattern.observer.observers.ITransactionObserver;

import java.util.ArrayList;
import java.util.List;

// El sujeto concreto
public class BankAccountSubject implements IAccountSubject {
    private String accountId;
    private double balance;
    // La lista de suscriptores (observadores)
    private final List<ITransactionObserver> observers = new ArrayList<>();

    public BankAccountSubject(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    @Override
    public void addObserver(ITransactionObserver observer) {
        System.out.println("SISTEMA: Agregando un nuevo observador: " + observer.getClass().getSimpleName());
        observers.add(observer);
    }

    @Override
    public void removeObserver(ITransactionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String transactionType, double amount) {
        // Notifica a todos los observadores suscritos
        for (ITransactionObserver observer : observers) {
            observer.update(this.accountId, transactionType, amount, this.balance);
        }
    }

    // La lógica de negocio ahora solo se preocupa de su estado y de notificar
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("\nRealizando un depósito de " + amount);
        // Notifica a los observadores sobre el cambio de estado
        notifyObservers("DEPOSITO", amount);
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("\nRealizando un retiro de " + amount);
            // Notifica a los observadores sobre el cambio de estado
            notifyObservers("RETIRO", amount);
        } else {
            System.out.println("ERROR: Saldo insuficiente.");
        }
    }
}
