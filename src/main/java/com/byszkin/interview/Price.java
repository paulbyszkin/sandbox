package com.byszkin.interview;

import java.math.BigDecimal;
import java.util.Currency;

import static org.apache.commons.lang.Validate.notNull;

public class Price {

    public final BigDecimal amount;
    public final Currency currency;


    public Price(BigDecimal amount, Currency currency) {

        notNull(amount);
        notNull(currency);

        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (amount != null ? !amount.equals(price.amount) : price.amount != null) return false;
        return currency != null ? currency.equals(price.currency) : price.currency == null;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
