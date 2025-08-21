package com.diesgut.concurrency.executor_service;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // Crear un ThreadGroup para agrupar los hilos
        ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");

        /*
        * Executors.newCachedThreadPool(). Un pool de hilos en caché reutilizará
        * los hilos existentes y creará nuevos cuando sea necesario (podemos agregar más consumidores).
        * Los hilos se terminan y se eliminan de la caché si no se han utilizado durante 60 segundos (podemos eliminar consumidores).
        * */
        ExecutorService executor = Executors.newCachedThreadPool(runnable -> {
            // Usamos un ThreadFactory que asigna los hilos al grupo creado
            Thread thread = new Thread(threadGroup, runnable);
           // thread.setDaemon(true); // Hacer hilos daemon para que no bloqueen la salida del programa
            return thread;
        });

        // Enviar 3 tareas rápidas
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            executor.submit(() -> {
                log.debug("Task {} started on {}",taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simula trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.debug("Task {} completed on {}",taskId, Thread.currentThread().getName());
            });
        }

        // Pausar para que las 3 tareas se inicien
        Thread.sleep(500);

        // Enviar una tarea lenta
        executor.submit(() -> {
            log.debug("Slow task started on {}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000); // Simula trabajo lento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.debug("Slow task completed on {}", Thread.currentThread().getName());
        });

        // Enviar 2 tareas más para ver cómo se reutilizan los hilos
        for (int i = 3; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                log.debug("Task {} started on {}",taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simula trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.debug("Task {} completed on {}",taskId, Thread.currentThread().getName());
            });
        }

        Thread[] activeThreads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(activeThreads);


        Utils.sleep(5000);

        log.debug("\nActive Threads in Group:");
        for (Thread t : activeThreads) {
            log.debug("Thread Name: " + t.getName());
        }


        // Esperar a que todas las tareas se completen
        executor.shutdown();
    }
}
