package com.diesgut.concurrency.executor_service.labs;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lab10 {
    public static void main(String... arg) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    log.debug("Executed task");
                }
            } catch (Exception e) {
                log.debug("Tarea interrumpida"); //no entra al catch
            }
        });
        Utils.sleep(3);
        //show "Executed task" by 3 milliseconds
        executor.shutdownNow();

    }
}
