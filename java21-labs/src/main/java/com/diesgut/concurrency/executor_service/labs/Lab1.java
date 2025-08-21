package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

@Slf4j
public class Lab1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); //se ejecutaran dos tareas de forma simultanea

        for (int i = 1; i <= 5; i++) {
            final int task = i;
            //las tareas no ejecutadas, se enviaran a un pool
            //no garantiza un orden, pero garantiza que todas se ejecutaran
            executor.submit(() -> {
                log.debug("Ejecutando task {}", task);
            });
        }
        /*
         * detiene tareas sin interrumpir las que estan en proceso, osea las que ya fueron enviadas a submit()
         * no se podran agregar nuevas tareas al executorService
         * */
        executor.shutdown();
        try {
            executor.submit(() -> {
                log.debug("Lanzo error");
            });
        } catch (Exception ex) {
            log.error("El error es " + RejectedExecutionException.class.getName());
        }

    }
}
