package com.diesgut.gof.creational_pattern.abstract_factory.factory.imp;

import com.diesgut.gof.creational_pattern.abstract_factory.factory.IFinancialReportFactory;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2b.BusinessLoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.imp.b2b.BusinessTransactionHistoryReport;

public class BusinessBankingReportFactory implements IFinancialReportFactory {
    @Override
    public ILoanStatusReport createLoanStatusReport() {
        return new BusinessLoanStatusReport();
    }

    @Override
    public ITransactionHistoryReport createTransactionHistoryReport() {
        return new BusinessTransactionHistoryReport();
    }
}