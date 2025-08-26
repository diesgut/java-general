package com.diesgut.gof.behaviour_pattern.memento.caretaker;

import com.diesgut.gof.behaviour_pattern.memento.BeneficiaryMemento;

import java.util.Stack;

// Caretaker: Guarda los Mementos pero no sabe qué contienen.
public class EditHistory {
    // Usamos una pila para gestionar fácilmente el "undo".
    private final Stack<BeneficiaryMemento> history = new Stack<>();

    public void push(BeneficiaryMemento memento) {
        System.out.println("CARETAKER: Guardando un Memento en el historial.");
        history.push(memento);
    }

    public BeneficiaryMemento pop() {
        System.out.println("CARETAKER: Obteniendo el último Memento del historial.");
        return history.pop();
    }
}