package com.diesgut.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
* Un latch (cerrojo) es un sincronizador en Java que permite que uno o más hilos esperen hasta que un conjunto de eventos en otros hilos se haya completado
* */
@Slf4j
public class Latches {
    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String... arg){
        log.debug("The server is getting ready to start ");
        log.debug("Starting services ...\n");

        long starting = System.currentTimeMillis();

        Thread service1 = new Thread(new ServerService(latch, "HTTP Listeners"));
        Thread service2 = new Thread(new ServerService(latch, "JMX"));
        Thread service3 = new Thread(new ServerService(latch, "Connectors"));

        service1.start();
        service2.start();
        service3.start();

        try {
            //Todos los hilos que llaman al método await() estarán bloqueados hasta que el contador llegue a cero.
            latch.await();
            log.debug("Server has successfully started in {} seconds", (System.currentTimeMillis() - starting) / 1000 );
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            // log ex
        }
    }
    @Slf4j
    public static class ServerService implements Runnable {
        private final String serviceName;
        private final CountDownLatch latch;
        private final Random rnd = new Random();

        public ServerService(CountDownLatch latch, String serviceName) {
            this.latch = latch;
            this.serviceName = serviceName;
        }

        @Override
        public void run() {

            int startingIn = rnd.nextInt(10) * 1000;

            try {
                log.debug("Starting service {}", serviceName);
                Thread.sleep(startingIn);

                log.debug("Service {} has successfully started in {} seconds", serviceName, (startingIn / 1000));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                // log ex
            } finally {
                latch.countDown();
                log.debug("Service {} running ...", serviceName);
            }
        }

    }
}
