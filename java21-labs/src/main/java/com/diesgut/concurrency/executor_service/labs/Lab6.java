package com.diesgut.concurrency.executor_service.labs;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lab6 {
    public static void main(String... ags) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Utils.sleep(2000);
            log.debug("Tarea 1 completa");
        });
        executor.submit(() -> {
            Utils.sleep(1000);
            log.debug("Tarea 2 completa");
        });
        executor.shutdown();
        log.debug("Shutdown llamado");
        /*
         * SHOW IN CONSOLE
         * Shutdown llamado
         * Tarea 2 completa
         * Tarea 1 completa
         *
        * */
    }

}
