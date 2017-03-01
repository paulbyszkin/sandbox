package com.byszkin.interview;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.byszkin.interview.OrderType.BUY;
import static com.byszkin.interview.Unit.KG;
import static java.util.Currency.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrdersRendererTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null() {

        OrdersRenderer ordersRenderer = new OrdersRenderer();
        ordersRenderer.renderOrders(null);

    }

    @Test
    public void should_return_empty_data() {
        OrdersRenderer ordersRenderer = new OrdersRenderer();
        SortedMap<Price, OrderQuantity> data = new TreeMap<>(new PriceComparator(BUY));
        String text = ordersRenderer.renderOrders(data);
        String expected = "";
        assertThat(text, is(expected));
    }

    @Test
    public void should_render_data() {

        OrdersRenderer ordersRenderer = new OrdersRenderer();
        SortedMap<Price, OrderQuantity> data = new TreeMap<>(new PriceComparator(BUY));
        data.put(new Price(new BigDecimal("17.4"), getInstance("GBP")), new OrderQuantity(new BigDecimal("25"), KG));
        data.put(new Price(new BigDecimal("19.2"), getInstance("GBP")), new OrderQuantity(new BigDecimal("27"), KG));
        String text = ordersRenderer.renderOrders(data);
        String expected = "- 27 KG for £19.2\n- 25 KG for £17.4";
        assertThat(text, is(expected));

    }

}