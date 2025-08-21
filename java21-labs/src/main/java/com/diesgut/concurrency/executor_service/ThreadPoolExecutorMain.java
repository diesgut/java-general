package com.diesgut.concurrency.executor_service;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolExecutorMain {

    static ExecutorService executor;

    public static void main(String... args) {
        //ExecutorService es una interfaz mas completa que executor, tiene implementaciones como ThreadPoolExecutor
        /**
         * Para optimizar el tamaño del pool, necesitamos recolectar la siguiente información:
         * Número de CPUs (Runtime.getRuntime().availableProcessors()) | Utilización de CPU objetivo (en el rango, [0, 1])  | Tiempo de espera (W) | Tiempo de cómputo (C)
         * Número de hilos = Número de CPUs * Utilización objetivo de CPU * (1 + W/C)
         * */
        /**
         * Como regla general, para tareas intensivas en cómputo (generalmente tareas pequeñas),
         * Puede ser una buena idea evaluar el pool de hilos con el número de hilos igual al número de procesadores o el número de procesadores + 1
         * */
        final AtomicInteger counter = new AtomicInteger();

        int corePoolSize = 10; //número de hilos que se deben mantener en el pool, incluso si están inactivos (a menos que se establezca allowCoreThreadTimeOut).
        int maximumPoolSize = 20; //El número máximo de hilos permitidos, aunque se tenga mas tareas en la cola, no se podran crear mas hilos
        long keepAliveTime = 1; //Cuando transcurre este tiempo, los hilos inactivos serán eliminados del pool (estos son los hilos inactivos que exceden el corePoolSize).
        TimeUnit unit = TimeUnit.SECONDS; // La unidad de tiempo para el argumento keepAliveTime.
        /*
         * Una cola para almacenar las instancias de Runnable (solo las tareas Runnable enviadas por el método execute()) antes de que sean ejecutadas.
         * Cuando el número de hilos activos alcanza el tamaño del corePoolSize (en este caso, 10), las tareas adicionales se encolarán en la cola, siempre y cuando la cola no esté llena.
         * Cuando el numero de hilos alcanza el corePoolSize y la workQueue esta llena, no se podra agregar mas tareas hasta que los hilos se liberen
         * Solo si se cumplio el punto anterior, el ThreadPoolExecutor empieza agregar nuevos hilos hasta alcanzar el maximumPoolSize
         * pero si hay mas de 20 (tamaño de maximumPoolSize) tareas pendientes y la workQueue esta llena, el rejectedHandler se activa para rechazar las tareas adicionales
         * */
        // (workQueue) = maximumPoolSize - corePoolSize -- En teoria
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5); // Una cola para almacenar las instancias de Runnable (solo las tareas Runnable enviadas por el método execute()) antes de que sean ejecutadas.

        //Esta fábrica se usa cuando el ejecutor crea un nuevo hilo.
        ThreadFactory threadFactory = (Runnable runnable) -> {
            String threadName = String.format("Cool-Thread-%s", counter.incrementAndGet());
            log.debug("Creating a new {}", threadName);
            return new Thread(runnable, threadName);
        };
        /*
         * Cuando ThreadPoolExecutor no puede ejecutar un Runnable debido a saturación, esto ocurre cuando los
         * límites de hilos y las capacidades de la cola están llenos (por ejemplo, si workQueue tiene un tamaño fijo y también se establece maximumPoolSize)
         * —esto da el control y la decisión a este manejador.
         * */
        /*
         * AbortPolicy (DEFAULT): Lanza una excepción (RejectedExecutionException) cuando una tarea es rechazada. Es la política por defecto.
         * CallerRunsPolicy: Hace que el hilo que intenta enviar la tarea ejecute la tarea rechazada. (Tareas rechazadas se ejecutaran en el hilo principal)
         * DiscardPolicy: Simplemente descarta la tarea rechazada sin hacer nada.
         * DiscardOldestPolicy: Descarta la tarea más antigua en la cola de trabajo y acepta la nueva tarea.
         * */
        RejectedExecutionHandler rejectedHandlerCallerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        RejectedExecutionHandler rejectedHandler = (Runnable runnable, ThreadPoolExecutor executor) -> {
            if (runnable instanceof SimpleThread task) {
                log.debug("Rejecting task {}", task.taskId);
            }
        };

                executor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedHandler);
        // ((ThreadPoolExecutor) executor).allowCoreThreadTimeOut(true); // Esto permite que los hilos inactivos se eliminen después de un tiempo si no están haciendo nada, lo que puede permitir que más hilos se utilicen para tareas nuevas.
        for (int i = 0; i < 50; i++) {
            executor.execute(new SimpleThread(i));
        }
        executor.shutdown();

        try {
            boolean isAwaitTermination = executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            log.debug("isAwaitTermination {}", isAwaitTermination);
        } catch (InterruptedException ex) {
            log.error("Error", ex);
            Thread.currentThread().interrupt();
        }
    }

    @Slf4j
    static class SimpleThread implements Runnable {
        private final int taskId;

        public SimpleThread(int taskId) {
            this.taskId = taskId;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            Utils.sleep(2000);
            log.debug("Executing task {} via {} queue {}", this.taskId, Thread.currentThread().getName(), ((ThreadPoolExecutor) executor).getQueue().size());
        }
    }

}
