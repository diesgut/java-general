package com.diesgut.concurrency.executor_service.use_case.producer_consumer.assembly_line;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        /*
        * ConcurrentLinkedQueue
        * Es una cola no bloqueante y de acceso concurrente que implementa una estructura de datos de cola FIFO (Primero en entrar, primero en salir).
        * No bloquea hilos, pero si la cola está vacía, un hilo que intente retirar un elemento no bloqueará, simplemente obtendrá null si la cola está vacía.
        * */
        AssemblyLine.startAssemblyLine(new ConcurrentLinkedQueue<>());
        Thread.sleep(10 * 1000);
        Queue<String> remainingTasks = AssemblyLine.stopAssemblyLine();

        Thread.sleep(2000);

        System.out.println("\nStarting assembly line again ...");

        AssemblyLine.startAssemblyLine(new ConcurrentLinkedQueue<>(remainingTasks));
        Thread.sleep(60 * 100);
        AssemblyLine.stopAssemblyLine();
    }

}
