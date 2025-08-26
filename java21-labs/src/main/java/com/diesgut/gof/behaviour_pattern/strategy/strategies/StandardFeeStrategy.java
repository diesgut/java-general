package com.diesgut.gof.behaviour_pattern.strategy.strategies;

public class StandardFeeStrategy implements IFeeCalculationStrategy {
    @Override
    public double calculate(double amount) {
        return amount * 0.015; // 1.5%
    }
}