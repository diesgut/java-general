package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lab4 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
                log.debug("Ejecuto la tarea");
            } catch (InterruptedException e) {
                log.debug("Tarea interrumpida");
            }
        });
        executor.shutdownNow();
        //muestra Tarea interrumpida"
    }
}
