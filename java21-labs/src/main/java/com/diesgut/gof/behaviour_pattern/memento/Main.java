package com.diesgut.gof.behaviour_pattern.memento;

import com.diesgut.gof.behaviour_pattern.memento.caretaker.EditHistory;
import com.diesgut.gof.behaviour_pattern.memento.originator.BeneficiaryAccount;

public class Main {
    public static void main(String[] args) {
        // Configuramos los 3 actores
        EditHistory history = new EditHistory();
        BeneficiaryAccount beneficiary = new BeneficiaryAccount("Jane Roe", "987-654-321", "Wells Fargo");

        System.out.println("Estado Original: " + beneficiary);

        // 1. El Caretaker le pide al Originador que guarde su estado
        history.push(beneficiary.save());

        // 2. El usuario realiza cambios en el Originador
        System.out.println("\nEditando beneficiario...");
        beneficiary.setName("Jane R. Roe");
        beneficiary.setBank("Citibank");
        System.out.println("Estado Modificado: " + beneficiary);

        // Podríamos incluso guardar un segundo estado
        // history.push(beneficiary.save());
        // beneficiary.setName("Jane Roe-Smith");
        // System.out.println("Segundo Estado Modificado: " + beneficiary);

        // 3. El usuario decide cancelar. El Caretaker devuelve el Memento al Originador.
        System.out.println("\nUsuario cancela la operación. Restaurando...");
        beneficiary.restore(history.pop());

        System.out.println("Estado Final Restaurado: " + beneficiary);
    }
}
