package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Lab2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {
            throw new RuntimeException("Error en la tarea");
        });
        try {
            future.get();
        } catch (Exception e) {
            log.debug("La clase es {}", e.getClass()); //ExecutionException
            log.debug("El mensaje {}", e.getMessage()); //ExecutionException java.lang.RuntimeException: Error en la tarea
        }
        executor.shutdown();

    }
}
