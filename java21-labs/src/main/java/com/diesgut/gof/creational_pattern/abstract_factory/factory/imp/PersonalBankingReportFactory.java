package com.diesgut.gof.creational_pattern.abstract_factory.factory.imp;

import com.diesgut.gof.creational_pattern.abstract_factory.factory.IFinancialReportFactory;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2c.PersonalLoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2c.PersonalTransactionHistoryReport;

public class PersonalBankingReportFactory implements IFinancialReportFactory {
    @Override
    public ILoanStatusReport createLoanStatusReport() {
        return new PersonalLoanStatusReport();
    }

    @Override
    public ITransactionHistoryReport createTransactionHistoryReport() {
        return new PersonalTransactionHistoryReport();
    }
}