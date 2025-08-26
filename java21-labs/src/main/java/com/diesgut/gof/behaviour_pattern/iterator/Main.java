package com.diesgut.gof.behaviour_pattern.iterator;

public class Main {
    public static void main(String[] args) {
        AccountVault vault = new AccountVault();
        AccountAuditor auditor = new AccountAuditor();
        auditor.generateReport(vault);
    }
}
