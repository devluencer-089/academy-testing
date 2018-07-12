package com.senacor.testing.granularity;

import java.util.Objects;

public class Email {

    private final String value;

    public Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
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
