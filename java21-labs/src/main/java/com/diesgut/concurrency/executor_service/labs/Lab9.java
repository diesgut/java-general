package com.diesgut.concurrency.executor_service.labs;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Lab9 {
    public static void main(String... arg) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Utils.sleep(2000);
            log.debug("Tarea completada");
        });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        //show Tarea completada
    }
}
