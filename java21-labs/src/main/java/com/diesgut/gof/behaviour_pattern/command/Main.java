package com.diesgut.gof.behaviour_pattern.command;

import com.diesgut.gof.behaviour_pattern.command.commands.DepositCommand;
import com.diesgut.gof.behaviour_pattern.command.commands.ITransaccionCommand;
import com.diesgut.gof.behaviour_pattern.command.commands.WithdrawalCommand;
import com.diesgut.gof.behaviour_pattern.command.invoker.TransactionsBroker;
import com.diesgut.gof.behaviour_pattern.command.receiver.BankAccountService;

public class Main {
    public static void main(String[] args) {
        // Creamos el Receptor
        BankAccountService cuenta = new BankAccountService("001-2345", 1000.00);

        // Creamos el Invocador
        TransactionsBroker broker = new TransactionsBroker();

        // Creamos los Comandos Concretos y los pasamos al Invocador
        ITransaccionCommand deposito = new DepositCommand(cuenta, 250.00);
        ITransaccionCommand retiro = new WithdrawalCommand(cuenta, 400.00);

        broker.takeTransaction(deposito);
        broker.takeTransaction(retiro);

        // El Invocador procesa las transacciones cuando sea necesario
        System.out.println("\nProcesando lote de transacciones...");
        broker.procesarTransacciones();
    }
}
