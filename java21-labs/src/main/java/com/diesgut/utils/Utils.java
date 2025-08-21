package com.diesgut.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (Exception e){
            log.error("Error sleep", e);
        }
    }
}
