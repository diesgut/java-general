package com.diesgut.concurrency.executor_service.labs;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Lab7 {
    public static void main(String... arg) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Utils.sleep(3000);
            log.debug("Tarea 1 completa");
        });
        executor.submit(() -> {
            Utils.sleep(3000);
            log.debug("Tarea 2 completa");
        });
        executor.shutdown();
        if (executor.awaitTermination(1, TimeUnit.SECONDS)) { //awaitTerminate needs InterruptedException
            log.debug("Timeout no alcanzado");
        } else {
            log.debug("Timeout alcanzado");
        }
        /*
        * Muestra:
        * Timeout alcanzado
        * Tarea 2 completa
        * Tarea 1 completa
        * */
    }
}
