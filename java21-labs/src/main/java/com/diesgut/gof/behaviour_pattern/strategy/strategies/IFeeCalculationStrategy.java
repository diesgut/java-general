package com.diesgut.gof.behaviour_pattern.strategy.strategies;

// La interfaz Strategy
@FunctionalInterface // Es una buena práctica ya que solo tiene un método
public interface IFeeCalculationStrategy {
    double calculate(double amount);
}