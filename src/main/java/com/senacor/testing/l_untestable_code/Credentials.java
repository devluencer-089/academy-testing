package com.senacor.testing.l_untestable_code;

import org.springframework.stereotype.Component;

@Component
//was SyncerConfig
public class Credentials {

    public static String get(String property) {
        //read from config store
        return "value";
    }

    public String getUsername() {
        return "username";
    }

    public String getPassword() {
        return "passw0rd";
    }
}
