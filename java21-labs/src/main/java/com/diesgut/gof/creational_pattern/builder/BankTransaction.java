package com.diesgut.gof.creational_pattern.builder;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class BankTransaction {
    // --- Campos Obligatorios ---
    @NonNull // Lombok generará una validación de nulidad para este campo
    private String transactionId;
    @NonNull
    private Double amount;
    @NonNull
    private String sourceAccount;
    @NonNull
    private String targetAccount;

    // --- Campos Opcionales ---
    private String description;
    private String category;

    // Podemos establecer valores por defecto usando @Builder.Default
    @Builder.Default
    private String currency = "USD";
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
