package com.diesgut.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/*
 * Es un sincronizador en Java que permite que un grupo de hilos (conocidos como partes)
 * lleguen a un punto común de barrera. Básicamente, un grupo de hilos espera a que todos se
 * encuentren en la barrera y luego avanzar.
 *
 * Este sincronizador funciona bien para problemas que dependen de una tarea que puede ser dividida en subtareas.
 * Cada subtarea se ejecuta en un hilo diferente y espera a que los demás hilos terminen.
 * Cuando todos los hilos completan su tarea, combinan sus resultados en un solo resultado.
 * */
@Slf4j
public class Barrier {

    public static void main(String... arg) {
        log.debug("The server is getting ready to start ");
        log.debug("Starting services ...\n");

        long starting = System.currentTimeMillis();

        Runnable barrierAction = () -> {
            log.debug("\"Services are ready to start ...\"");
        };
        CyclicBarrier barrier = new CyclicBarrier(3, barrierAction);
        //Todas las subtareas se encontraran en el barrier await y se ejecutara el barrierAction()
        /*
         * Podemos saber cuántas partes son necesarias para activar esta barrera a través del
         * método getParties() y cuántos hilos están esperando actualmente en la barrera a través del método getNumberWaiting().
         * */


        Thread service1 = new Thread(new ServerService(barrier, "HTTP Listeners"));
        Thread service2 = new Thread(new ServerService(barrier, "JMX"));
        Thread service3 = new Thread(new ServerService(barrier, "Connectors"));

        service1.start();
        service2.start();
        service3.start();

        try {
            service1.join();
            service2.join();
            service3.join();

            log.debug("Server has successfully started in {} seconds", (System.currentTimeMillis() - starting) / 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            // log ex
        }
    }

    @Slf4j
    public static class ServerService implements Runnable {

        private final String serviceName;
        private final CyclicBarrier barrier;
        private final Random rnd = new Random();

        public ServerService(CyclicBarrier barrier, String serviceName) {
            this.barrier = barrier;
            this.serviceName = serviceName;
        }

        @Override
        public void run() {

            int startingIn = rnd.nextInt(10) * 1000;

            try {
                log.debug("Starting service {}", serviceName);
                Thread.sleep(startingIn);

                log.debug("Service '{}' was prepared in {}", serviceName, (startingIn / 1000));

                /*
                 * Este método puede esperar indefinidamente o hasta el tiempo de espera especificado (si transcurre el tiempo de espera especificado o si
                 *         el hilo es interrumpido, este hilo se libera con una TimeoutException;
                 * la barrera se considera rota y todos los hilos esperando en la barrera se liberan con una BrokenBarrierException).
                 * */
                barrier.await();

                log.debug("The service {} is running ...", serviceName);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                log.error("Exception", ex);
            } catch (BrokenBarrierException ex) {
                log.error("Exception ... barrier is broken!", ex);
            }
        }

    }
}
