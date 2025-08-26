package com.diesgut.gof.behaviour_pattern.command.commands;

import com.diesgut.gof.behaviour_pattern.command.receiver.BankAccountService;

// --- 3. Comandos Concretos (Concrete Commands) ---
// Implementaciones de la interfaz Command. Cada una encapsula una solicitud específica.
public class DepositCommand implements ITransaccionCommand {
    private final BankAccountService bankAccountService;
    private double amount;

    public DepositCommand(BankAccountService bankAccountService, double amount) {
        this.bankAccountService = bankAccountService;
        this.amount = amount;
    }

    @Override
    public void execute() {
        bankAccountService.deposit(amount);
    }
}