package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lab5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> {
                    Thread.sleep(100);
                    return 1;
                },
                () -> {
                    Thread.sleep(200);
                    return 2;
                },
                () -> {
                    Thread.sleep(300);
                    return 3;
                }
        );
        Integer result = executor.invokeAny(tasks); //need ExecutionException, InterruptedException
        log.debug("Resultado {}", result); //Resultado 1
        executor.shutdown();

    }
}
