package com.diesgut.gof.creational_pattern.abstract_factory.factory;

import com.diesgut.gof.creational_pattern.abstract_factory.products.ILoanStatusReport;
import com.diesgut.gof.creational_pattern.abstract_factory.products.ITransactionHistoryReport;

public interface IFinancialReportFactory {
    ILoanStatusReport createLoanStatusReport();
    ITransactionHistoryReport createTransactionHistoryReport();
}
