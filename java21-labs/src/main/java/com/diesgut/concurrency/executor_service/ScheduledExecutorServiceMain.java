package com.diesgut.concurrency.executor_service;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorServiceMain {
    public static void main(String... arg){
        // Crear un ScheduledExecutorService con un solo hilo
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Programar una tarea para que se ejecute después de 5 segundos
        scheduler.schedule(() -> {
            log.debug("Tarea de una sola vez ejecutada después de 5 segundos");
        }, 3, TimeUnit.SECONDS);

        Utils.sleep(4000);

        // Programar una tarea periódica que se ejecute cada 3 segundos
        scheduler.scheduleAtFixedRate(() -> {
            log.debug("Tarea periódica ejecutada cada 3 segundos");
        }, 0, 3, TimeUnit.SECONDS);
        Utils.sleep(5000);

        // Programar una tarea con un retraso fijo entre ejecuciones
        scheduler.scheduleWithFixedDelay(() -> {
            log.debug("Tarea ejecutada con retraso fijo de 2 segundos entre ejecuciones");
        }, 0, 2, TimeUnit.SECONDS);
    }
}
