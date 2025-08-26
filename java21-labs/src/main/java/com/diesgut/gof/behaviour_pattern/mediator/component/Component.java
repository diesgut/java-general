package com.diesgut.gof.behaviour_pattern.mediator.component;

import com.diesgut.gof.behaviour_pattern.mediator.mediator.ITransactionMediator;

// Clase base (o interfaz) para todos los componentes que participarán
public abstract class Component {
    protected ITransactionMediator mediator;

    public void setMediator(ITransactionMediator mediator) {
        this.mediator = mediator;
    }
}