package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Lab8 {
    public static void main(String... arg) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> result = executor.submit(() -> {
            return 10 + 20;
        });
        log.debug("Result {}", result.get()); //get needs ExecutionException, InterruptedException
        executor.shutdown();
        //show Result 30
    }
}
