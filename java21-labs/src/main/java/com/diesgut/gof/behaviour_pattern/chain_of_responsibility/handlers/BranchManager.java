package com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers;

import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.LoanRequest;

// Manejador Concreto 2: Gerente de Sucursal
public class BranchManager extends Approver {
    @Override
    public void processRequest(LoanRequest request) {
        if (request.getAmount() <= 50000) {
            System.out.printf("El Gerente de Sucursal aprobó la solicitud de $%.2f\n", request.getAmount());
        } else if (nextApprover != null) {
            System.out.println("-> El Gerente de Sucursal no puede aprobar. Pasando al Director Regional...");
            nextApprover.processRequest(request);
        }
    }
}