package com.diesgut.gof.behaviour_pattern.strategy.strategies;

public class VipFeeStrategy implements IFeeCalculationStrategy {
    @Override
    public double calculate(double amount) {
        return 2.0; // $2.00 fijos
    }
}