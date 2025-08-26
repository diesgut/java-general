package com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers;

import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.LoanRequest;

// Manejador Concreto 1: Oficial de Préstamos
public class LoanOfficer extends Approver {
    @Override
    public void processRequest(LoanRequest request) {
        if (request.getAmount() <= 10000) {
            System.out.printf("El Oficial de Préstamos aprobó la solicitud de $%.2f\n", request.getAmount());
        } else if (nextApprover != null) {
            System.out.println("-> El Oficial de Préstamos no puede aprobar. Pasando al Gerente de Sucursal...");
            nextApprover.processRequest(request);
        }
    }
}