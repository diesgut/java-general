package com.diesgut.gof.behaviour_pattern.chain_of_responsibility;

import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers.Approver;
import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers.BranchManager;
import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers.LoanOfficer;
import com.diesgut.gof.behaviour_pattern.chain_of_responsibility.handlers.RegionalDirector;

public class Main {
    public static void main(String[] args) {
        // 1. Crear los eslabones de la cadena
        Approver officer = new LoanOfficer();
        Approver manager = new BranchManager();
        Approver director = new RegionalDirector();

        // 2. Construir la cadena de responsabilidad
        officer.setNext(manager);
        manager.setNext(director);

        // 3. Enviar solicitudes a la cadena
        System.out.println("--- Procesando solicitud de $8,000 ---");
        officer.processRequest(new LoanRequest(8000));

        System.out.println("\n--- Procesando solicitud de $45,000 ---");
        officer.processRequest(new LoanRequest(45000));

        System.out.println("\n--- Procesando solicitud de $150,000 ---");
        officer.processRequest(new LoanRequest(150000));

        System.out.println("\n--- Procesando solicitud de $300,000 ---");
        officer.processRequest(new LoanRequest(300000));
    }
}
