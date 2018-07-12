package com.senacor.testing;

import java.util.UUID;

public class GUID {

    public static String random() {
        return UUID.randomUUID().toString();
    }
}
