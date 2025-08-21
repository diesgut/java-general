package com.diesgut.concurrency.executor_service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class InvokeAll {
    private static final Random rnd = new Random();
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final Consumer consumer = new Consumer();
    private static ExecutorService consumerService;

    public static void main(String... arg) {
        for (int i = 0; i < 100; i++) {
            queue.offer("bulb-" + rnd.nextInt(1000));
        }

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            tasks.add(consumer);
        }

        log.info("Submit all tasks and wait for all of them to complete ...");

        consumerService = Executors.newWorkStealingPool();
        // consumerService = Executors.newCachedThreadPool();
        // consumerService = Executors.newWorkStealingPool(PROCESSORS);
        // consumerService = Executors.newFixedThreadPool(PROCESSORS);

        try {
            List<Future<String>> futures = consumerService.invokeAll(tasks);

            for (Future<String> future : futures) {
                String bulb = future.get();
                log.info("Future done: {}", bulb);
            }

            consumerService.shutdown();
            consumerService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.error("Exception: ", ex);
        } catch (ExecutionException ex) {
            log.error("Exception: ", ex.getCause());
        }
    }

    private static class Consumer implements Callable {
        @Override
        public String call() throws InterruptedException {
            String bulb = queue.poll();
            Thread.sleep(100);
            if (bulb != null) {
                log.debug("Packed: {} by consumer: {}", bulb, Thread.currentThread().getName());
                return bulb;
            }
            return "";
        }
    }
}
