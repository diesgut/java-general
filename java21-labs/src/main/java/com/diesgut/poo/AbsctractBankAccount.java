package com.diesgut.poo;

public abstract class AbsctractBankAccount {

    // --- ATRIBUTOS PRIVADOS ---
    // Nadie fuera de esta clase puede acceder a ellos directamente. Ni siquiera las clases hijas.
    private String accountNumber;
    private double balance;

    /**
     * Constructor para inicializar los datos comunes de una cuenta bancaria.
     *
     * @param accountNumber El número único que identifica la cuenta.
     */
    public AbsctractBankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Toda nueva cuenta inicia con saldo cero.
    }

    // --- MÉTODOS CONCRETOS ---
    // Estos métodos tienen una implementación común para todos los tipos de cuenta.

    /**
     * Deposita una cantidad de dinero en la cuenta.
     * Este comportamiento es igual para una cuenta de ahorros, corriente, etc.
     *
     * @param amount La cantidad a depositar (debe ser positiva).
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit of $" + amount + " successful. New balance: $" + this.balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // --- MÉTODOS PÚBLICOS (GETTERS) ---
    // Proveen acceso de solo lectura a los atributos privados.

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    // --- MÉTODO PROTEGIDO (SETTER INTERNO) ---
    // Este método solo será visible para las clases hijas (paquete y subclases).
    // Permite que las implementaciones concretas modifiquen el saldo,
    // pero la clase base mantiene el control.
    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    // --- MÉTODOS ABSTRACTOS ---
    // Estos métodos NO tienen implementación aquí.
    // Forzamos a que cada clase hija (concreta) defina su propio comportamiento.

    /**
     * Retira una cantidad de dinero de la cuenta.
     * La lógica específica (si permite sobregiros, etc.) depende del tipo de cuenta.
     *
     * @param amount La cantidad a retirar.
     */
    public abstract void withdraw(double amount);

    /**
     * Aplica las comisiones de mantenimiento mensuales.
     * Cada tipo de cuenta puede tener una política de comisiones diferente.
     */
    public abstract void applyMonthlyMaintenance();
}