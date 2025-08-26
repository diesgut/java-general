package com.diesgut.gof.behaviour_pattern.mediator.component;

public class ComplianceService extends Component {
    public void checkCompliance() {
        // Lógica de cumplimiento...
        System.out.println("Verificación de cumplimiento normativo... OK.");
        // Notifica al mediador que la verificación pasó
        super.mediator.notify(this, "complianceCheckPassed");
    }
}
