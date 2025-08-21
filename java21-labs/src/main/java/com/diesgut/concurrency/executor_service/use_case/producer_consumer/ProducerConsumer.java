package com.diesgut.concurrency.executor_service.use_case.producer_consumer;

import com.diesgut.utils.Utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

    private static final int MAX_CAPACITY = 10; // Capacidad máxima de la cola

    public static void main(String[] args) {
        // Creamos una cola bloqueante con capacidad máxima
        //BlockingQueue el productor espera si la cola esta llena y el consumidor si la cola esta vacia
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(MAX_CAPACITY);

        // Creamos el productor
        Thread producer = new Thread(new Producer(queue));

        // Creamos el consumidor
        Thread consumer = new Thread(new Consumer(queue));

        // Iniciamos los hilos
        producer.start();
        consumer.start();
    }

    // Clase Productor
    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 20; i++) { // El productor produce 20 elementos
                    System.out.println("Produciendo: " + i);
                    queue.put(i); // Espera si la cola está llena
                    Utils.sleep(500); // Simulamos tiempo de producción
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase Consumidor
    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer item = queue.take(); // Espera si la cola está vacía
                    System.out.println("Consumido: " + item);
                    Utils.sleep(1000); // Simulamos tiempo de consumo
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
