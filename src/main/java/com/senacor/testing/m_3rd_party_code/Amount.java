package com.senacor.testing.m_3rd_party_code;

import java.math.BigInteger;
import java.util.Objects;

public class Amount {

    public static final Amount ZERO_EUR = new Amount(BigInteger.ZERO, "EUR");

    private final BigInteger amount;
    private final String currency;

    public Amount(BigInteger amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Amount other = (Amount) obj;
        return Objects.equals(this.amount, other.amount)
                && Objects.equals(this.currency, other.currency);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
