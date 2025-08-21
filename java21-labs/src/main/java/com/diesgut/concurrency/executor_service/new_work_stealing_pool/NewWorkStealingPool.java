package com.diesgut.concurrency.executor_service.use_case.producer_consumer.new_work_stealing_pool;

import lombok.extern.slf4j.Slf4j;

/*
 * que crea un ExecutorService basado en un pool de hilos que utiliza el algoritmo de "work-stealing".
 * El algoritmo de work-stealing es una técnica de programación en la que los hilos inactivos "roban" trabajo de otros hilos
 * que están ocupados, ayudando a distribuir la carga de trabajo de manera más eficiente entre los hilos.
 * Esto es particularmente útil en entornos donde las tareas son pequeñas y la sobrecarga de gestionar múltiples hilos es significativa.
 * */
/*
 * En un pool de hilos clasicos  se basa en una cola interna de entrada para almacenar las tareas.
 * Cada hilo debe quitar una tarea de la cola y ejecutarla. Esto es adecuado para casos en los que las tareas consumen mucho tiempo y su número es relativamente bajo
 * Por otro lado, si estas tareas son muchas y son pequeñas (requieren poco tiempo para ser ejecutadas), habrá muchas contenciones también.
 * Para reducir las contenciones y aumentar el rendimiento, un pool de hilos puede basarse en un algoritmo de robo de trabajo y una cola por hilo.
 *           En este caso, hay una cola central de entrada para todas las tareas
 *           y una cola extra (conocida como la cola de tareas local) para cada hilo (hilo trabajador)
 *           Cada hilo toma tareas de la cola central y los pone en su cola, he ira procesando estas tareas mientras su cola no este vacia
 *           si su cola esta vacia, robara tareas de otras colas
 *           Si dos hilos intentan robar de la misma cola local, entonces hay contención, pero normalmente esto debería ser insignificante.
 *
 *  NewWorkStealingPool usa el número de procesadores disponibles como su nivel de paralelismo objetivo.
 *  Esto está disponible a través de Executors.newWorkStealingPool() y Executors.newWorkStealingPool(int parallelism).
 * Este tipo de pool es recomendado para tareas que no pasen de los 10 segundos (NewWorkStealingPool internamente instancia ForkJoinPool )
 * CONTENCION => varios recursos intentan acceder al mismo recurso
 *
 * NewWorkStealingPool() es un nivel de abstracción más alto para ForkJoinPool
 * Internamente tiene un ForkJoinPool donde:
 *       Nivel de paralelistmo es la cantidad de procesadores disponibles Runtime.getRuntime().availableProcessors()
 *       La fabrica de hilos es la predeterminada ForkJoinPool.defaultForkJoinWorkerThreadFactory
 *       El manejador de excepciones es null
 *       El asyncMode configurado en true, para habilitar el modo FIFO , First in First Out, para que las tarjeas de forkean y nunca de joinean
 * */
@Slf4j
public class NewWorkStealingPool {

}
