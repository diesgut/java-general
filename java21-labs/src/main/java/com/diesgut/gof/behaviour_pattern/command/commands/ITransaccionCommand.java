package com.diesgut.gof.behaviour_pattern.command.commands;

// --- 1. La Interfaz Command ---
// Define una operación que se puede ejecutar. Es la "Comanda".
// La interfaz Comando normalmente declara un único método para ejecutar el comando.
public interface ITransaccionCommand {
    void execute();
}