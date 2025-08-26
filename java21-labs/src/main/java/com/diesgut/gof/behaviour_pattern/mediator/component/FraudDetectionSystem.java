package com.diesgut.gof.behaviour_pattern.mediator.component;

public class FraudDetectionSystem extends Component {
    public void checkTransaction() {
        // Lógica de fraude...
        System.out.println("Verificación de fraude... OK.");
        // Notifica al mediador que la verificación pasó
        super.mediator.notify(this, "fraudCheckPassed");
    }
}