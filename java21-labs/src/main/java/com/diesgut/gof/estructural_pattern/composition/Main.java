package com.diesgut.gof.estructural_pattern.composition;

import com.diesgut.gof.estructural_pattern.composition.composite.Portfolio;
import com.diesgut.gof.estructural_pattern.composition.leaf.Stock;

public class Main {
    public static void main(String[] args) {
        // 1. Crear activos individuales (Hojas)
        Stock appleStock = new Stock("AAPL", 10, 150.0); // Valor: 1500
        Stock googleStock = new Stock("GOOGL", 5, 2800.0); // Valor: 14000

        // 2. Crear un portafolio de tecnología (Compuesto)
        Portfolio techPortfolio = new Portfolio("Tech Portfolio");
        techPortfolio.addAsset(appleStock);
        techPortfolio.addAsset(googleStock);

        // 3. Crear otro activo individual
        Stock bankStock = new Stock("JPM", 20, 160.0); // Valor: 3200

        // 4. Crear un portafolio principal que contiene todo
        Portfolio mainPortfolio = new Portfolio("Main Portfolio");
        mainPortfolio.addAsset(techPortfolio); // Agregamos un portafolio dentro de otro
        mainPortfolio.addAsset(bankStock);

        // 5. Calcular el valor total con una sola llamada
        // El cliente no necesita saber la estructura interna del portafolio.
        // Trata al portafolio principal como un único FinancialAsset.
        System.out.printf("Valor total del portafolio de Tecnología: $%.2f\n", techPortfolio.getValue());
        System.out.printf("Valor total del portafolio Principal: $%.2f\n", mainPortfolio.getValue());
    }
}
