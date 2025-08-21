package com.diesgut.concurrency.executor_service.use_case.producer_consumer.new_work_stealing_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class ManySmallTasks {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int MAX_PROD_WORKS = 15_000_000;

    private static final Random rnd = new Random();
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private static final Consumer consumer = new Consumer();
    private static long startTime;
    private static ExecutorService consumerService;

    public static void main(String... arg){
        ManySmallTasks main = new ManySmallTasks();
        main.manySmallTasks();
    }

    public void manySmallTasks() {
        simulatingProducers();
        startConsumers();
    }

    public void fewBigTasks() {

    }

    private static void startConsumers() {
        log.debug("We have a consumers team of {} members", PROCESSORS);

       consumerService = Executors.newWorkStealingPool(); //1470 ms
       //  consumerService = Executors.newCachedThreadPool(); //19937 ms
       //  consumerService = Executors.newWorkStealingPool(PROCESSORS); //1311 ms
       //  consumerService = Executors.newFixedThreadPool(PROCESSORS); //5094 ms
        int queueSize = queue.size();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < queueSize; i++) {
            consumerService.execute(consumer);
        }

        consumerService.shutdown();
        try {
            consumerService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
          //  Logger.getLogger(AssemblyLine.class.getName()).log(Level.SEVERE, null, ex);
            log.error("ERROR", ex);
        }
    }

    private static void simulatingProducers() {
        log.debug("Simulating the job of the producers overnight ...");
        log.info("The producers checked {} works ...", MAX_PROD_WORKS);

        for (int i = 0; i < MAX_PROD_WORKS; i++) {
            queue.offer("work-" + rnd.nextInt(1000));
        }
    }

    @Slf4j
    private static class Consumer implements Runnable {

        @Override
        public void run() {
            String bulb = queue.poll();
            if (bulb != null) {
                // process the bulb here
               // log.info("Packed: {} by consumer: ", Thread.currentThread().getName());
            }

            if (queue.isEmpty()) {
                log.debug("The consumers team packed all bulbs in {} ms", System.currentTimeMillis() - startTime);
                log.debug("Note: It is possible to see the above message multiple times...");

                System.exit(0);
            }
        }

    }
}
