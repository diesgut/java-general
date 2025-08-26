package com.diesgut.gof.behaviour_pattern.state_v2.state;

import com.diesgut.gof.behaviour_pattern.state_v2.context.CreditCard;

public interface ICardState {
    void makePurchase(CreditCard context, double amount);
    void payBalance(CreditCard context, double amount);
    void suspend(CreditCard context);
    void activate(CreditCard context);
    void cancel(CreditCard context);
}
