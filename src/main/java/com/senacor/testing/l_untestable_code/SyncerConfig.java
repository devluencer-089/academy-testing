package com.senacor.testing.l_untestable_code;

public class SyncerConfig {
    private static final SyncerConfig INSTANCE = new SyncerConfig();

    public static SyncerConfig getInstance() {
        return INSTANCE;
    }

    private SyncerConfig() {

    }

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
