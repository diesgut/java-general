package com.diesgut.gof.creational_pattern.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BankTransaction transaction = BankTransaction.builder()
                .transactionId("TXN-12346")
                .amount(500.00)
                .sourceAccount("001-123456-01")
                .targetAccount("003-555555-03")
                .build();
        log.debug("Transferencia Simple: {}", transaction);
    }
}
