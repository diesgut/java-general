package com.diesgut.gof.behaviour_pattern.observer.subjects;

import com.diesgut.gof.behaviour_pattern.observer.observers.ITransactionObserver;

// Interfaz para el sujeto (o clase abstracta)
public interface IAccountSubject {
    void addObserver(ITransactionObserver observer);
    void removeObserver(ITransactionObserver observer);
    void notifyObservers(String transactionType, double amount);
}