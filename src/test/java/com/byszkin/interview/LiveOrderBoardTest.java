package com.byszkin.interview;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.byszkin.interview.OrderType.*;
import static com.byszkin.interview.Unit.KG;
import static java.util.Currency.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LiveOrderBoardTest {

    private LiveOrderBoard liveOrderBoard;

    @Before
    public void setUp() throws Exception {
        liveOrderBoard = new LiveOrderBoard(new OrdersRenderer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_on_registering_null() {

        liveOrderBoard.registerOrder(null);
    }

    @Test
    public void should_register_single_order() {

        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));

        assertThat(liveOrderBoard.getBuyOrders().size(), is(1));
        assertThat(liveOrderBoard.getBuyOrders().get(new Price(new BigDecimal("32"), getInstance("GBP"))).get(0), is(getOrder(1, "14.2", "32", BUY)));

        assertThat(liveOrderBoard.getSellOrders().size(), is(0));
    }

    @Test
    public void should_register_duplicated_order() {

        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));
        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));

        assertThat(liveOrderBoard.getBuyOrders().size(), is(1));
        assertThat(liveOrderBoard.getBuyOrders().get(getOrder(1, "14.2", "32", BUY).pricePerUnit).get(0), is(getOrder(1, "14.2", "32", BUY)));
        assertThat(liveOrderBoard.getBuyOrders().get(getOrder(1, "14.2", "32", BUY).pricePerUnit).get(1), is(getOrder(1, "14.2", "32", BUY)));

        assertThat(liveOrderBoard.getSellOrders().size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_on_cancelling_null() {
        liveOrderBoard.cancelOrder(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_on_cancelling_non_existing_order() {
        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));

        Order nonExistingOrder = getOrder(2, "14.2", "32", BUY);

        liveOrderBoard.cancelOrder(nonExistingOrder);
    }

    @Test
    public void should_cancel_single_order() {
        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));

        liveOrderBoard.cancelOrder(getOrder(1, "14.2", "32", BUY));

        assertThat(liveOrderBoard.getBuyOrders().size(), is(0));

        assertThat(liveOrderBoard.getSellOrders().size(), is(0));
    }

    @Test
    public void should_cancel_duplicated_order() {

        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));
        liveOrderBoard.registerOrder(getOrder(1, "14.2", "32", BUY));

        liveOrderBoard.cancelOrder(getOrder(1, "14.2", "32", BUY));

        assertThat(liveOrderBoard.getBuyOrders().size(), is(1));
        assertThat(liveOrderBoard.getBuyOrders().get(new Price(new BigDecimal("32"), getInstance("GBP"))).get(0), is(getOrder(1, "14.2", "32", BUY)));

        assertThat(liveOrderBoard.getSellOrders().size(), is(0));

    }

    @Test
    public void should_print_empty_summary() {

        String summary = liveOrderBoard.getSummary();

        String expected = "\n\n";

        assertThat(summary, is(expected));

    }

    @Test
    public void should_print_board() {

        liveOrderBoard.registerOrder(getOrder(3, "19.5", "34", SELL));
        liveOrderBoard.registerOrder(getOrder(1, "14.5", "32", SELL));
        liveOrderBoard.registerOrder(getOrder(1, "17.5", "32", SELL));
        liveOrderBoard.registerOrder(getOrder(2, "14.5", "32", SELL));

        liveOrderBoard.registerOrder(getOrder(5, "12.5", "62", BUY));
        liveOrderBoard.registerOrder(getOrder(6, "16.5", "42", BUY));

        String summary = liveOrderBoard.getSummary();

        String expected = "- 46.5 KG for £32\n- 19.5 KG for £34\n\n- 12.5 KG for £62\n- 16.5 KG for £42";

        assertThat(summary, is(expected));
    }

    private Order getOrder(int userId, String quantity, String amount, OrderType orderType) {
        return new Order(userId, new OrderQuantity(new BigDecimal(quantity), KG), new Price(new BigDecimal(amount), getInstance("GBP")), orderType);
    }

}