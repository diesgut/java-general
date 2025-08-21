package com.diesgut.concurrency.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

@Slf4j
public class Lab1 {
    public static void main(String... arg){
        Lab1 lab = new Lab1();
        lab.run();
    }

    public void run(){
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        simpleExecutor.execute(() -> {
            log.debug("La clase mas simple para ejecucion de tareas, expone el metodo execute()");
        });
    }

    public class SimpleExecutor implements Executor {
        @Override
        public void execute(Runnable r) {
            (new Thread(r)).start();
        }
    }
}
