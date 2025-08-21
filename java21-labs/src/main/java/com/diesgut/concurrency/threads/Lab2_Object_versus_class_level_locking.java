package com.diesgut.concurrency.threads;

/**
 * En Java, un bloque de código marcado como sincronizado puede ser ejecutado por un solo hilo a la vez.
 * Dado que Java es un entorno multihilo (soporta concurrencia), necesita un mecanismo de sincronización para
 * evitar problemas específicos de entornos concurrentes (por ejemplo, interbloqueos y consistencia de memoria).
 * Un hilo puede adquirir bloqueos a nivel de objeto o a nivel de clase.
 */
public class Lab2_Object_versus_class_level_locking {
    public static void main(String... args) {
        Lab2_Object_versus_class_level_locking lab2 = new Lab2_Object_versus_class_level_locking();
        // lab2.notSynchronizedMethod(); //execute successfully
        //  lab2.nonStaticMethodV1(); //execute successfully
        //  lab2.nonStaticMethodV2(); //Only one thread is executed
        lab2.staticMethod(); //Execute threat 1 aftest 1 second execute threat 2
    }

    public void notSynchronizedMethod() {
        Thread t1 = new Thread(() -> {
            ConcurrentClass concurrentClass = new ConcurrentClass();
            concurrentClass.notSynchronizedMethod();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            ConcurrentClass concurrentClass = new ConcurrentClass();
            concurrentClass.notSynchronizedMethod();
        });
        t2.start();
    }

    public void nonStaticMethodV1() {
        Thread t1 = new Thread(() -> {
            ConcurrentClass concurrentClass = new ConcurrentClass();
            concurrentClass.nonStaticMethod();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            ConcurrentClass concurrentClass = new ConcurrentClass();
            concurrentClass.nonStaticMethod();
        });
        t2.start();
    }

    public void nonStaticMethodV2() {
        ConcurrentClass concurrentClass = new ConcurrentClass();
        Thread t1 = new Thread(concurrentClass::nonStaticMethod);
        t1.start();
        Thread t2 = new Thread(concurrentClass::nonStaticMethod);
        t2.start();
    }

    public void staticMethod() {
        Thread t1 = new Thread(ConcurrentClass::staticMethod);
        t1.start();
        Thread t2 = new Thread(ConcurrentClass::staticMethod);
        t2.start();
    }

    /**
     * Bloqueo a nivel de objeto
     * El bloqueo a nivel de objeto se puede lograr marcando un bloque de código no estático o un método no estático
     * (el objeto de bloqueo para el objeto de ese método) con synchronized.
     * En los siguientes ejemplos, solo un hilo a la vez podrá ejecutar el método/bloque sincronizado en una instancia dada de la clase:
     */
    class ClassOll1 {
        public synchronized void methodOll() {

        }
    }

    class ClassOll2 {
        public void methodOll() {
            synchronized (this) {

            }
        }
    }

    class ClassOll {
        private final Object ollLock = new Object();

        public void methodOll() {
            synchronized (ollLock) {
            }
        }
    }

    /**
     * Bloqueo a nivel de clase
     * Para proteger los datos estáticos, el bloqueo a nivel de clase se puede lograr
     * marcando un método/bloque estático
     * o adquiriendo un bloqueo sobre la referencia .class con synchronized.
     * En los siguientes ejemplos, solo un hilo de una de las instancias disponibles en tiempo de ejecución podrá ejecutar el bloque sincronizado a la vez:
     */

    public class ClassCll1 {
        public synchronized static void methodCll() {
        }
    }

    public class ClassCll2 {
        public void method() {
            synchronized (ClassCll2.class) {
            }
        }
    }

    public class ClassCll3 {
        private final static Object aLock = new Object();

        public void method() {
            synchronized (aLock) {
            }
        }
    }
}

