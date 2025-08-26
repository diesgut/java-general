package com.diesgut.gof.behaviour_pattern.strategy.strategies;

public class NoFeeStrategy implements IFeeCalculationStrategy {
    @Override
    public double calculate(double amount) {
        return 0.0;
    }
}