package com.diesgut.gof.behaviour_pattern.iterator;

import java.util.Iterator;
import java.util.List;

// --- La Colección Concreta (Concrete Aggregate) ---
// Ahora implementa la interfaz Iterable<Cuenta>.
// Esto es una promesa: "Te garantizo que te daré un iterador para recorrer mis cuentas".
public class AccountVault implements Iterable<Account> {
    private List<Account> accounts;

    public AccountVault() {
        this.accounts = new java.util.ArrayList<>();
        accounts.add(new Account("001", 5000, "SAVINGS"));
        accounts.add(new Account("002", 12000, "CHECKING"));
        accounts.add(new Account("003", 250, "SAVINGS"));
    }

    // Ya no exponemos la lista interna. El método getCuentas() se elimina.
    // En su lugar, implementamos el método de la interfaz Iterable.
    @Override
    public Iterator<Account> iterator() {
        // Delegamos la creación del iterador a la propia lista, que ya sabe cómo hacerlo.
        return this.accounts.iterator();
    }
}
