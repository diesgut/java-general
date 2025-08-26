package com.diesgut.gof.estructural_pattern.proxy;

// El "Subject": define la interfaz común para el objeto real y el proxy.
// Tanto el objeto real como el proxy implementarán esta interfaz. El cliente solo conocerá esta abstracción.
public interface IAccountService {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}