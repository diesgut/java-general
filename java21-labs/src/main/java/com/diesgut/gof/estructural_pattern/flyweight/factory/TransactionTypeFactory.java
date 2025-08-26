package com.diesgut.gof.estructural_pattern.flyweight.factory;

import com.diesgut.gof.estructural_pattern.flyweight.flyweight.ConcreteTransactionType;
import com.diesgut.gof.estructural_pattern.flyweight.flyweight.ITransactionType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

// La Fábrica de Flyweights
@Slf4j
public class TransactionTypeFactory {
    private static final Map<String, ITransactionType> flyweights = new HashMap<>();

    public static ITransactionType getTransactionType(String name) {
        return flyweights.computeIfAbsent(name, k -> new ConcreteTransactionType(name));
    }

    public static int getFlyweightCount() {
        return flyweights.size();
    }
}