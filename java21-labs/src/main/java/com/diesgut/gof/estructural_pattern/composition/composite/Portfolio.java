package com.diesgut.gof.estructural_pattern.composition.composite;

import com.diesgut.gof.estructural_pattern.composition.component.FinancialAsset;

import java.util.ArrayList;
import java.util.List;

// El Compuesto: representa un nodo que puede tener hijos (hojas u otros compuestos).
public class Portfolio implements FinancialAsset {
    private String name;
    private List<FinancialAsset> assets = new ArrayList<>();

    public Portfolio(String name) {
        this.name = name;
    }

    public void addAsset(FinancialAsset asset) {
        assets.add(asset);
    }

    public void removeAsset(FinancialAsset asset) {
        assets.remove(asset);
    }

    @Override
    public double getValue() {
        // El valor de un portafolio es la suma de los valores de todos sus activos.
        // Aquí está la magia: simplemente itera y llama a getValue() en cada hijo,
        // sin importar si es una Acción o otro Portafolio.
        double totalValue = 0;
        for (FinancialAsset asset : assets) {
            totalValue += asset.getValue();
        }
        return totalValue;
    }
}
