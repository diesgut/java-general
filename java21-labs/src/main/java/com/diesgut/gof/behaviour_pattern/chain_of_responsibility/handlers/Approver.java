package com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers;

import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.LoanRequest;

// El "Handler": define la interfaz para manejar las solicitudes.
public abstract class Approver {
    protected Approver nextApprover; // Referencia al siguiente en la cadena

    public void setNext(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    // Método para procesar la solicitud
    public abstract void processRequest(LoanRequest request);
}