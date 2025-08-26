package com.diesgut.gof.behaviour_pattern.strategy.strategies;

public class WeekendFeeStrategy implements IFeeCalculationStrategy {
    @Override
    public double calculate(double amount) {
        return amount * 0.02; // 2.0%
    }
}