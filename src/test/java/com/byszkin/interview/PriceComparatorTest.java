package com.byszkin.interview;

import org.junit.Test;

import java.math.BigDecimal;

import static com.byszkin.interview.OrderType.*;
import static java.util.Currency.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PriceComparatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void order_type_must_not_be_null() {
        new PriceComparator(null);
    }

    @Test
    public void should_sort_amount_for_buy() {
        PriceComparator comparator = new PriceComparator(BUY);
        int result = comparator.compare(new Price(new BigDecimal("17"), getInstance("GBP")), new Price(new BigDecimal("18"), getInstance("AUD")));
        assertThat(result, is(1));
    }

    @Test
    public void should_sort_currency_for_buy() {
        PriceComparator comparator = new PriceComparator(BUY);
        int result = comparator.compare(new Price(new BigDecimal("17"), getInstance("GBP")), new Price(new BigDecimal("17"), getInstance("AUD")));
        assertThat(result, is(6));
    }

    @Test
    public void should_sort_amount_for_sell() {
        PriceComparator comparator = new PriceComparator(SELL);
        int result = comparator.compare(new Price(new BigDecimal("17"), getInstance("GBP")), new Price(new BigDecimal("18"), getInstance("AUD")));
        assertThat(result, is(-1));
    }

    @Test
    public void should_sort_currency_for_sell() {
        PriceComparator comparator = new PriceComparator(SELL);
        int result = comparator.compare(new Price(new BigDecimal("17"), getInstance("GBP")), new Price(new BigDecimal("17"), getInstance("AUD")));
        assertThat(result, is(6));
    }


}