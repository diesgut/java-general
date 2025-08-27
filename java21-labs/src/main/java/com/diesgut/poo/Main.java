package com.diesgut.poo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Creamos una lista de tipo BankAccount.
        // Gracias al polimorfismo, esta lista puede contener CUALQUIER objeto
        // que sea un "hijo" o implementación de BankAccount.
        List<AbsctractBankAccount> allAccounts = new ArrayList<>();

        // Añadimos diferentes tipos de cuentas a la misma lista.
        allAccounts.add(new SavingsAccount("SA-101"));
        allAccounts.add(new CheckingAccount("CA-202", 150.0));
        allAccounts.add(new SavingsAccount("SA-102"));
        allAccounts.add(new CheckingAccount("CA-203", 50.0));

        // 1. Vamos a depositar diferentes cantidades en cada cuenta.
        System.out.println("--- Processing Initial Deposits ---");
        allAccounts.get(0).deposit(150);  // Saldo SA-101: 150
        allAccounts.get(1).deposit(200);  // Saldo CA-202: 200
        allAccounts.get(2).deposit(500);  // Saldo SA-102: 500
        allAccounts.get(3).deposit(30);   // Saldo CA-203: 30
        System.out.println("=".repeat(40));

        // 2. AHORA LA MAGIA DEL POLIMORFISMO ✨
        // Vamos a llamar al MISMO método (withdraw) en CADA objeto de la lista.
        // Java sabrá automáticamente qué versión del método `withdraw` ejecutar.
        System.out.println("\n--- Processing Withdrawals (Polymorphism in action) ---");
        for (AbsctractBankAccount account : allAccounts) {
            System.out.print("[" + account.getAccountNumber() + "] attempting to withdraw $180... ");

            // La misma línea de código se comporta de forma diferente
            // dependiendo del objeto real (SavingsAccount o CheckingAccount).
            account.withdraw(180);
        }
        System.out.println("=".repeat(40));

        // 3. Lo mismo sucede con cualquier otro método polimórfico.
        // Apliquemos el mantenimiento mensual a todas las cuentas.
        System.out.println("\n--- Applying Monthly Maintenance Fees ---");
        for (AbsctractBankAccount account : allAccounts) {
            System.out.print("[" + account.getAccountNumber() + "] applying fee... ");
            account.applyMonthlyMaintenance();
        }
        System.out.println("=".repeat(40));

        // 4. Verifiquemos los saldos finales.
        System.out.println("\n--- Final Account Balances ---");
        for (AbsctractBankAccount account : allAccounts) {
            System.out.printf("Account %s final balance: $%.2f%n",
                    account.getAccountNumber(), account.getBalance());
        }
    }
}
