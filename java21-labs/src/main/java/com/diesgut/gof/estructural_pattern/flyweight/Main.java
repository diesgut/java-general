package com.diesgut.gof.estructural_pattern.flyweight;

public class Main {
    public static void main(String[] args) {
        // Creamos múltiples transacciones
        new Transaction(100.0, "Depósito").process();
        new Transaction(50.0, "Retiro").process();
        new Transaction(200.0, "Depósito").process(); // Reutilizará el flyweight "Depósito"
        new Transaction(75.0, "Retiro").process();   // Reutilizará el flyweight "Retiro"
    }
}
