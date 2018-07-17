package com.senacor.testing;

import java.util.Objects;
import java.util.Random;

public class Email {

    private final String value;

    public Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public static Email random() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        return Email.of(saltStr + "@gamil.com");
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "value='" + value + '\'' +
                '}';
    }
}
