package com.diesgut.gof.behaviour_pattern.iterator;

import java.util.List;

// --- El Cliente (El Auditor) ---
// Ahora el auditor es mucho más simple y desacoplado.
public class AccountAuditor {
    public void generateReport(AccountVault vault) {
        System.out.println("--- STARTING AUDIT REPORT ---");

        // ¡Magia! El "for-each loop" de Java funciona con cualquier objeto que sea "Iterable".
        // El auditor ya no sabe ni le importa cómo están guardadas las cuentas.
        for (Account account : vault) {
            System.out.println(account);
        }

        System.out.println("--- END OF AUDIT REPORT ---");
    }
}
