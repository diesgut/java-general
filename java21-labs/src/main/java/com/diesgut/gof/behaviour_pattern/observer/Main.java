package com.diesgut.gof.behaviour_pattern.observer;

import com.diesgut.gof.behaviour_pattern.observer.observers.AuditLoggerObserver;
import com.diesgut.gof.behaviour_pattern.observer.observers.DashboardUpdaterObserver;
import com.diesgut.gof.behaviour_pattern.observer.observers.EmailNotifierObserver;
import com.diesgut.gof.behaviour_pattern.observer.observers.ITransactionObserver;
import com.diesgut.gof.behaviour_pattern.observer.subjects.BankAccountSubject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Observer Pattern");
        // 1. Crear el Sujeto
        BankAccountSubject account = new BankAccountSubject("ACC-789", 5000.0);

        // 2. Crear los Observadores
        ITransactionObserver emailer = new EmailNotifierObserver();
        ITransactionObserver logger = new AuditLoggerObserver();
        ITransactionObserver dashboard = new DashboardUpdaterObserver();

        // 3. Suscribir los Observadores al Sujeto
        account.addObserver(emailer);
        account.addObserver(logger);
        account.addObserver(dashboard);

        // 4. Realizar operaciones que cambiarán el estado y notificarán a todos
        account.deposit(500.0);
        account.withdraw(200.0);

        // 5. Podemos quitar un observador dinámicamente
        System.out.println("\nSISTEMA: El servicio de email entra en mantenimiento. Dando de baja...");
        account.removeObserver(emailer);

        account.withdraw(100.0); // Esta operación ya no enviará email
    }
}
