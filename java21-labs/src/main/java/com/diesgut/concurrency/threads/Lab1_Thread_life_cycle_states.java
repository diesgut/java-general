package com.diesgut.concurrency.threads;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * Para escribir clases seguras para hilos, podemos considerar las siguientes técnicas:
 * No tener estado (clases sin variables de instancia ni estáticas)
 * Tener estado, pero no compartirlo (por ejemplo, usar variables de instancia a través de Runnable, ThreadLocal, etc.)
 * Tener estado, pero un estado inmutable
 * Usar paso de mensajes (por ejemplo, como en el framework Akka)
 * Usar bloques sincronizados
 * Usar variables volatile
 * Usar estructuras de datos del paquete java.util.concurrent
 * Usar sincronizadores (por ejemplo, CountDownLatch y Barrier)
 * Usar bloqueos del paquete java.util.concurrent.locks
* */
@Slf4j
public class Lab1_Thread_life_cycle_states {
    public static void main(String[] args) {
        Lab1_Thread_life_cycle_states nt = new Lab1_Thread_life_cycle_states();
        nt.newAndRunnableState(); //NEW AND RUNNABLE
        nt.blockingState(); //BLOCKED STATE
        nt.waitingState(); //WAITING
        nt.timedWaitingState(); //TIMED_WAITING
        nt.terminatedState(); //TERMINATED
    }

    public void newAndRunnableState() {
        Thread t = new Thread(() -> {
        });
        log.debug("NewThread: " + t.getState()); // NEW
        t.start();
        log.debug("NewThread: " + t.getState()); // RUNNABLE

    }

    public void blockingState() {
        Thread t1 = new Thread(new SyncCode());
        Thread t2 = new Thread(new SyncCode());

        t1.start();
        Utils.sleep(2000);

        t2.start();
        Utils.sleep(2000);

        log.debug("Final BlockedThread t1: {} ({})", t1.getState(), t2.getName()); // RUNNABLE STATE
        log.debug("Final BlockedThread t2: {} ({})", t2.getState(), t2.getName()); //BLOCKED STATE
        System.exit(0);
    }

    public static  Thread t1 = null;
    public void waitingState() {
        t1 = new Thread(() -> {
            Thread t2 = new Thread(() -> {
                Utils.sleep(2000);
                log.debug("WaitingThread t1: {}", Lab1_Thread_life_cycle_states.t1.getState()); //WAITING
            });
            t2.start();

            try {
                t2.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                // log ex
            }
        });
        t1.start();
    }

    public void timedWaitingState() {
        Thread t1 = new Thread(() -> {
            Utils.sleep(2000);
        });
        t1.start();
        Utils.sleep(500);
        log.debug("TimedWaitingThread t: {}", t1.getState()); // TIMED_WAITING
    }

    public void terminatedState() {
        Thread t = new Thread(() -> {});
        t.start();
        Utils.sleep(1000);
        log.debug("TerminatedThread t: {}", t.getState()); // TERMINATED
    }

    class SyncCode implements Runnable {
        @Override
        public void run() {
            log.debug("Thread {} is in run() method", Thread.currentThread().getName());
            syncMethod();
        }

        public static synchronized void syncMethod() {
            log.debug("Thread {} is in syncMethod() method", Thread.currentThread().getName());
            while (true) {
                // t1 will stay here forever, therefore t2 is blocked
            }
        }
    }

}

