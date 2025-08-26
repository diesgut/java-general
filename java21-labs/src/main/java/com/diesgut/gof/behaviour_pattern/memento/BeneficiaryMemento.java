package com.diesgut.gof.behaviour_pattern.memento;

// Memento: Un objeto de datos inmutable que contiene el estado.
// Es una "caja negra" para el Caretaker.
public record BeneficiaryMemento(String name, String accountNumber, String bank) {
    // Los 'records' son inmutables por defecto, lo que es ideal para un Memento.
}
