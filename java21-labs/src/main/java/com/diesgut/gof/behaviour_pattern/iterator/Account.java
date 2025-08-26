package com.diesgut.gof.behaviour_pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Account {
    private String id;
    private double balance;
    private String type; // "SAVINGS", "CHECKING"
}
