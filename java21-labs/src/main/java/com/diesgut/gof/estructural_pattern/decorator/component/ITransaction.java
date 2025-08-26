package com.diesgut.gof.estructural_pattern.decorator.component;

// El Componente: define la interfaz para los objetos que pueden ser decorados.
// La interfaz común para el objeto original y todos los decoradores.
public interface ITransaction {
    double getCost();
    String getDescription();
}