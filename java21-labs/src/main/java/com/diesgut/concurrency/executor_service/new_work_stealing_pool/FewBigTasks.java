package com.diesgut.concurrency.executor_service.use_case.producer_consumer.new_work_stealing_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class FewBigTasks {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int MAX_PROD_BULBS = 15_000_000;
    private static final int CHUNK_BULBS = 1_000_000;

    private static final Random rnd = new Random();
    private static final Queue<BlockingQueue<String>> chunks = new LinkedBlockingQueue<>();

    private static long startTime;
    private static ExecutorService consumerService;

    //Esta vez, parece que el grupo de subprocesos que robaba trabajo funcionaba como un grupo de subprocesos normal.
    public static void main(String... arg) {
        simulatingProducers();
        startConsumers();
    }

    private static void startConsumers() {

        log.info("We have a consumers team of {} members", PROCESSORS);
        log.info("After split in chunks there are {} chunks of {} bulbs", chunks.size(), CHUNK_BULBS);


        consumerService = Executors.newWorkStealingPool(); //471 msF
        // consumerService = Executors.newCachedThreadPool(); //235 ms
        //consumerService = Executors.newWorkStealingPool(PROCESSORS); //453 ms
        // consumerService = Executors.newFixedThreadPool(PROCESSORS); //224 ms

        startTime = System.currentTimeMillis();
        while (!chunks.isEmpty()) {
            Consumer consumer = new Consumer(chunks.poll());
            consumerService.execute(consumer);
        }

        consumerService.shutdown();
        try {
            consumerService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.error("Errror", ex);
        }

    }

    @SuppressWarnings("unchecked")
    private static Queue<BlockingQueue<String>> simulatingProducers() {

        log.info("Simulating the job of the producers overnight ...");
        log.info("The producers checked {} bulbs ...", MAX_PROD_BULBS);

        int counter = 0;
        while (counter < MAX_PROD_BULBS) {
            BlockingQueue chunk = new LinkedBlockingQueue<>(CHUNK_BULBS);
            for (int i = 0; i < CHUNK_BULBS; i++) {
                chunk.offer("bulb-" + rnd.nextInt(1000));
            }

            chunks.offer(chunk);
            counter += CHUNK_BULBS;
        }

        return chunks;
    }

    @Slf4j
    private static class Consumer implements Runnable {

        private static final AtomicInteger countBulbs = new AtomicInteger();
        private final BlockingQueue<String> bulbs;

        public Consumer(BlockingQueue<String> bulbs) {
            this.bulbs = bulbs;
        }

        @Override
        public void run() {
            while (!bulbs.isEmpty()) {

                String bulb = bulbs.poll();

                if (bulb != null) {
                    // process the bulb here
                    // logger.info(() -> "Packed: " + bulb + " by consumer: "
                    //   + Thread.currentThread().getName());
                }

                countBulbs.incrementAndGet();
            }

            if (countBulbs.get() == MAX_PROD_BULBS) {
                log.info("The consumers team packed all bulbs in {} ms", (System.currentTimeMillis() - startTime));
                log.info("Note: It is possible to see the above message multiple times...");

                System.exit(0);
            }
        }
    }
}
