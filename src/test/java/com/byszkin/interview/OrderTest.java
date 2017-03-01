package com.byszkin.interview;

import org.junit.Test;

import static com.byszkin.interview.OrderType.*;
import static com.byszkin.interview.Unit.*;
import static java.math.BigDecimal.ONE;
import static java.util.Currency.getInstance;

public class OrderTest {

    @Test(expected = IllegalArgumentException.class)
    public void order_quantity_cant_be_null(){
        new Order(1, null, new Price(ONE, getInstance("GBP")), BUY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void price_cant_be_null(){
        new Order(1, new OrderQuantity(ONE, KG), null, SELL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void order_type_cant_be_null(){
        new Order(1, new OrderQuantity(ONE, KG), new Price(ONE, getInstance("GBP")), null);
    }

}