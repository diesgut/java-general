package com.diesgut.gof.creational_pattern.abstract_factory;

import com.diesgut.gof.creational_pattern.abstract_factory.factory.IFinancialReportFactory;
import com.diesgut.gof.creational_pattern.abstract_factory.factory.imp.BusinessBankingReportFactory;
import com.diesgut.gof.creational_pattern.abstract_factory.factory.imp.PersonalBankingReportFactory;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;

public class Main {
    public static void main(String[] args) {
        IFinancialReportFactory factory;
        String customerType = "BUSINESS"; // Esto podría venir de la sesión del usuario

        // Decidimos qué fábrica usar
        if ("PERSONAL".equalsIgnoreCase(customerType)) {
            factory = new PersonalBankingReportFactory();
        } else {
            factory = new BusinessBankingReportFactory();
        }

        // Usamos la fábrica para crear la familia de productos
        ILoanStatusReport loanReport = factory.createLoanStatusReport();
        ITransactionHistoryReport transactionReport = factory.createTransactionHistoryReport();

        // Generamos los reportes
        System.out.println(loanReport.generate());
        System.out.println(transactionReport.generate());
    }
}
