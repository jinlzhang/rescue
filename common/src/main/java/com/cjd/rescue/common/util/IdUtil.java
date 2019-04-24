package com.cjd.rescue.common.util;

import java.util.UUID;

public class IdUtil {

    public static String generateID(){
        return UUID.randomUUID().toString();
    }

}
