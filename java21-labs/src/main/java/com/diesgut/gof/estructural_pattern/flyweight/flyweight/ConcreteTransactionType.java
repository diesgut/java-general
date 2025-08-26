package com.diesgut.gof.estructural_pattern.flyweight.flyweight;

public class ConcreteTransactionType implements ITransactionType {
    // Estado Intrínseco (pesado y repetitivo)
    private final String name;
    private final byte[] icon; // Imaginemos que es la data de un ícono

    public ConcreteTransactionType(String name) {
        this.name = name;
        this.icon = (name + "-icon").getBytes(); // Simulación de data de ícono
        System.out.println("Creando nuevo Flyweight para el tipo: " + name);
    }

    //podriamos agregar logica para manejar el icono si fuera necesario
    @Override
    public void processTransaction(double amount) {
        System.out.printf("Procesando transacción de tipo '%s' por el monto de $%.2f\n", name, amount);
    }
}