package com.diesgut.gof.behaviour_pattern.command.commands;

import com.diesgut.gof.behaviour_pattern.command.receiver.BankAccountService;

public class WithdrawalCommand implements ITransaccionCommand {
    private final BankAccountService bankAccountService;
    private double amount;

    public WithdrawalCommand(BankAccountService account, double amount) {
        this.bankAccountService = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        bankAccountService.withdrawal(amount);
    }
}