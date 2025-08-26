package com.diesgut.gof.estructural_pattern.proxy;

public class Main {
    public static void main(String[] args) {
        // Se crea la cuenta real
        BankAccountService realAccount = new BankAccountService(1000);

        // --- Escenario 1: Un usuario no autorizado intenta retirar ---
        System.out.println("--- Intento de acceso por 'GUEST' ---");
        IAccountService guestProxy = new ServiceAccountProxy(realAccount, "GUEST");
        guestProxy.withdraw(100);
        System.out.printf("Saldo actual: $%.2f\n\n", guestProxy.getBalance());

        // --- Escenario 2: El propietario retira fondos ---
        System.out.println("--- Intento de acceso por 'OWNER' ---");
        IAccountService ownerProxy = new ServiceAccountProxy(realAccount, "OWNER");
        ownerProxy.deposit(200); // El depósito pasa directamente
        ownerProxy.withdraw(500);
        System.out.printf("Saldo actual: $%.2f\n", ownerProxy.getBalance());
    }
}
