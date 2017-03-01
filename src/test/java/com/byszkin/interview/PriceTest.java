package com.byszkin.interview;

import org.junit.Test;

import static java.math.BigDecimal.ONE;
import static java.util.Currency.getInstance;

public class PriceTest {

    @Test(expected = IllegalArgumentException.class)
    public void amount_cant_be_null(){
        new Price(null, getInstance("GBP"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void currency_cant_be_null(){
        new Price(ONE, null);
    }

}