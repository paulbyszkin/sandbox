package com.byszkin.interview;

import org.junit.Test;

import static com.byszkin.interview.Unit.*;
import static java.math.BigDecimal.ONE;

public class OrderQuantityTest {

    @Test(expected = IllegalArgumentException.class)
    public void quantity_cant_be_null(){
        new OrderQuantity(null, KG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unit_cant_be_null(){
        new OrderQuantity(ONE, null);
    }

}