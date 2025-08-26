package com.diesgut.gof.behaviour_pattern.memento.originator;

import com.diesgut.gof.behaviour_pattern.memento.BeneficiaryMemento;

// Originator: El objeto con el estado que queremos guardar.
public class BeneficiaryAccount {
    private String name;
    private String accountNumber;
    private String bank;

    // ... Constructor y otros métodos de negocio ...
    public BeneficiaryAccount(String name, String accountNumber, String bank) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    // --- Métodos del patrón Memento ---

    // Crea un Memento conteniendo un snapshot de su estado actual.
    public BeneficiaryMemento save() {
        System.out.println("ORIGINATOR: Guardando estado en un Memento.");
        return new BeneficiaryMemento(this.name, this.accountNumber, this.bank);
    }

    // Restaura su estado desde un objeto Memento.
    public void restore(BeneficiaryMemento memento) {
        System.out.println("ORIGINATOR: Restaurando estado desde un Memento.");
        this.name = memento.name();
        this.accountNumber = memento.accountNumber();
        this.bank = memento.bank();
    }

    // --- Métodos para modificar el estado (ejemplo) ---
    public void setName(String name) {
        this.name = name;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "BeneficiaryAccount{" + "name='" + name + '\'' + ", accountNumber='" + accountNumber + '\'' + ", bank='" + bank + '\'' + '}';
    }
}