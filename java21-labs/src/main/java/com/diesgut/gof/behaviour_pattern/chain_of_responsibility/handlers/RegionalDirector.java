package com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers;

import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.LoanRequest;

// Manejador Concreto 3: Director Regional
public class RegionalDirector extends Approver {
    @Override
    public void processRequest(LoanRequest request) {
        if (request.getAmount() <= 200000) {
            System.out.printf("El Director Regional aprobó la solicitud de $%.2f\n", request.getAmount());
        } else {
            System.out.printf("La solicitud de $%.2f no puede ser aprobada por esta cadena.\n", request.getAmount());
        }
    }
}