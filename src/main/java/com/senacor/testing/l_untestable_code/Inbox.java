package com.senacor.testing.l_untestable_code;

public class Inbox {

    private final String username;
    private final String password;

    private Inbox(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public static Inbox get(String username, String password) {
        return new Inbox(username, password);
    }

    public void add(Message message) {

    }
}
