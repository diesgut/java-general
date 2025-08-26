package com.diesgut.gof.behaviour_pattern.command.invoker;

import com.diesgut.gof.behaviour_pattern.command.commands.ITransaccionCommand;
import com.diesgut.gof.estructural_pattern.decorator.component.ITransaction;

import java.util.List;

// --- 4. El Invocador (Invoker) ---
// No sabe nada sobre depósitos o retiros. Solo sabe cómo tomar y ejecutar comandos.
// Es el "Mesero".
public class TransactionsBroker {
    private List<ITransaccionCommand> transactionsQueue = new java.util.ArrayList<>();

    public void takeTransaction(ITransaccionCommand transaction) {
        transactionsQueue.add(transaction);
    }

    public void procesarTransacciones() {
        for (ITransaccionCommand transactions : transactionsQueue) {
            transactions.execute(); // ¡El broker solo llama a ejecutar!
        }
        transactionsQueue.clear();
    }
}
