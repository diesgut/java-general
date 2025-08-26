package com.diesgut.gof.behaviour_pattern.chain_of_responsibility;

public class LoanRequest {
    private final double amount;
    public LoanRequest(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }
}
