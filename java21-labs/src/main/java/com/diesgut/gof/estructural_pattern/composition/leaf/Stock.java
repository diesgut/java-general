package com.diesgut.gof.estructural_pattern.composition.leaf;

import com.diesgut.gof.estructural_pattern.composition.component.FinancialAsset;

// La Hoja: representa un objeto individual en la estructura.
public class Stock implements FinancialAsset {
    private String symbol;
    private int quantity;
    private double pricePerShare;

    public Stock(String symbol, int quantity, double pricePerShare) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
    }

    @Override
    public double getValue() {
        // El valor de una acción es simplemente cantidad * precio.
        return quantity * pricePerShare;
    }
}
