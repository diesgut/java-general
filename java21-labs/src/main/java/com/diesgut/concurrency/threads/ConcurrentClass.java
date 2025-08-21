package com.diesgut.concurrency.threads;

import com.diesgut.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrentClass {
    public void notSynchronizedMethod() {
        log.debug("notSynchronizedMethod(): " + Thread.currentThread().getName());
        while (true) {
        }
    }

    public synchronized void nonStaticMethod() {
        log.debug("nonStaticMethod(): " + Thread.currentThread().getName());
        while (true) {
        }
    }

    public synchronized static void staticMethod() {
        log.debug("staticMethod(): " + Thread.currentThread().getName());
        Utils.sleep(1000);
    }
}
