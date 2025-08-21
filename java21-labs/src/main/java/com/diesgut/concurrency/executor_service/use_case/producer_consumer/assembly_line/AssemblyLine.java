package com.diesgut.concurrency.executor_service.use_case.producer_consumer.assembly_line;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class AssemblyLine {

    private AssemblyLine() {
        throw new AssertionError("There is a single assembly line!");
    }

    private static final int MAX_PROD_TIME_MS = 1 * 1000;
    private static final int MAX_CONS_TIME_MS = 3 * 1000;
    private static final int TIMEOUT_MS = MAX_PROD_TIME_MS + MAX_CONS_TIME_MS + 1000;
    private static final int PRODUCERS = 3;
    private static final int CONSUMERS = 2;

    private static final Random rnd = new Random();
    /*
     * LinkedTransferQueue
     * Es una cola bloqueante y transferente, lo que significa que está diseñada para permitir que los productores transfieran elementos a los consumidores de manera eficiente.
     * Si el consumidor no está listo, el productor esperará o "transferirá" el elemento.
     * Soporta operaciones bloqueantes como take() y poll() (con tiempos de espera), así como transfer(), que permite un paso de datos sin bloqueos entre los hilos.
     * */
    private static final Queue<String> queue = new LinkedTransferQueue<>(); //extiende de blocking queue
    //si queremos permitir que el productor procese mas rapido que el consumidor, podemos usar una cola tipo ConcurrentLinkedQueue

    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;
    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();

    private static ExecutorService producerService;
    private static ExecutorService consumerService;

    private static class Producer implements Runnable {
        @Override
        public void run() {
            while (runningProducer) {
                String bulb = "bulb-" + rnd.nextInt(1000);
                Utils.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
               /* queue.offer(bulb); //intenta agregar elementos de manera no bloqueante
                log.info("Checked: " + bulb);*/
                try {
                    boolean transfered = ((LinkedTransferQueue)queue).tryTransfer(bulb,
                            TIMEOUT_MS, TimeUnit.MILLISECONDS); // intentar transferir un elemento de manera no bloqueante a un consumidor, si no hay consumidor el elemento no se envia a la cola
                    if (transfered) {
                        log.debug("Checked: " + bulb);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Exception: ", e);
                    break;
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            while (runningConsumer) {
                String bulb = null;
               //bulb = queue.poll(); //no bloquea, si la cola esta vacia el metodo devuelve null, toma y elimina el elemento
                try {
                    bulb = (String) ((LinkedTransferQueue)queue).poll(MAX_PROD_TIME_MS, TimeUnit.MILLISECONDS); //bloquea hasta el tiempo de espera especificado, si es necesario, para que una bombilla esté disponible
                    //bulb = (String) ((LinkedTransferQueue)queue).take(); //el método bloquea al hilo que llama hasta que haya un elemento disponible en la cola. toma y elimina el elemento
                    if (bulb != null) {
                        Utils.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                        log.info("Packed: " + bulb);
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    log.error("Exception: ", ex);
                    break;
                }
            }
        }
    }

    public static void startAssemblyLine(Queue<String> remainingTasks) {

        if (runningProducer || runningConsumer) {
            log.info("Assembly line is already running ...");
            return;
        }

        log.info("Starting assembly line ...");
        log.info("Remaining bulbs from previous run: \n" + queue + "\n\n");

        queue.addAll(remainingTasks);

        runningProducer = true;
        //envia bombillas solo si el consumidor no esta ocupado
        //no procesara la siguiente bombilla hasta pasar la bombilla actual
       // producerService = Executors.newSingleThreadExecutor(); //Pool with a one thread
        producerService = Executors.newFixedThreadPool(PRODUCERS);
        producerService.execute(producer); //Producer thread

        runningConsumer = true;
        //intentara empacar las bombillas lo antes posible
      //  consumerService = Executors.newSingleThreadExecutor(); //Pool with a one thread
        consumerService = Executors.newFixedThreadPool(CONSUMERS);;
        consumerService.execute(consumer); //Consumer thread
    }

    public static Queue<String> stopAssemblyLine() {

        log.info("Stopping assembly line ...");

        boolean isProducerDown = shutdownProducer();
        boolean isConsumerDown = shutdownConsumer();

        if (!isProducerDown || !isConsumerDown) {
            log.error("Something abnormal happened during shutting down the assembling line!");
            System.exit(0);
        }

        log.info("Assembling line was successfully stopped!");

        return queue;
    }

    private static boolean shutdownProducer() {

        runningProducer = false;
        return shutdownExecutor(producerService);
    }

    private static boolean shutdownConsumer() {

        runningConsumer = false;
        return shutdownExecutor(consumerService);
    }

    private static boolean shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();

                return executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            }

            return true;
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            log.error("Exception: ", ex);
        }
        return false;
    }
}
