package com.diesgut.concurrency.executor_service.labs;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Lab3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> 1,
                () -> 2,
                () -> 3
        );
        List<Future<Integer>> results = executor.invokeAll(tasks); //needs InterruptedException
        for (Future<Integer> result : results) {
            log.debug("Result {}", result.get()); //Future.get() need ExecutionException
        }
        executor.shutdown();
    }

}
