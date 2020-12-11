package com.radhio.cvsender.Utils;

import java.util.UUID;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class Generator {
    public static String tsyncIDGenerator(){
        return UUID.randomUUID().toString();
    }
    public static long onSpotTime(){
        return System.currentTimeMillis();
    }
}
