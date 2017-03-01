package com.byszkin.interview;

import java.util.*;

import static com.byszkin.interview.OrderType.BUY;
import static com.byszkin.interview.OrderType.SELL;
import static com.byszkin.interview.Unit.*;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.*;
import static java.util.Map.*;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang.Validate.notNull;

public class LiveOrderBoard {

    private final SortedMap<Price, List<Order>> buyOrders = new TreeMap<>(new PriceComparator(BUY));
    private final SortedMap<Price, List<Order>> sellOrders = new TreeMap<>(new PriceComparator(SELL));

    private final OrdersRenderer ordersRenderer;

    public LiveOrderBoard(OrdersRenderer ordersRenderer) {
        this.ordersRenderer = ordersRenderer;
    }

    public void registerOrder(Order order) {

        notNull(order);

        Map<Price, List<Order>> orders = getOrdersByOrderType(order.orderType);

        if (orders.containsKey(order.pricePerUnit))
            orders.get(order.pricePerUnit).add(order);
        else {
            List<Order> list = new LinkedList<>();
            list.add(order);
            orders.put(order.pricePerUnit, list);
        }
    }

    public void cancelOrder(Order order) {

        notNull(order);

        Map<Price, List<Order>> orders = getOrdersByOrderType(order.orderType);

        if (!orders.containsKey(order.pricePerUnit) || !orders.get(order.pricePerUnit).contains(order)) {
            throw new IllegalArgumentException("Order: " + order + " didn't exist");
        } else {
            List<Order> orderList = orders.get(order.pricePerUnit);
            orderList.remove(order);
            if (orderList.isEmpty()) {
                orders.remove(order.pricePerUnit);
            }

        }
    }

    public String getSummary() {

        Map<Price, OrderQuantity> flatSellOrders = sellOrders.entrySet().stream().collect(toMap(Entry::getKey, o -> o.getValue().stream().map(a -> a.orderQuantity).reduce(new OrderQuantity(ZERO, KG), (a, b) -> new OrderQuantity(a.quantity.add(b.quantity), a.unit)), (a, b) -> a, () -> new TreeMap<>(new PriceComparator(SELL))));
        Map<Price, OrderQuantity> flatBuyOrders = buyOrders.entrySet().stream().collect(toMap(Entry::getKey, o -> o.getValue().stream().map(a -> a.orderQuantity).reduce(new OrderQuantity(ZERO, KG), (a, b) -> new OrderQuantity(a.quantity.add(b.quantity), a.unit)), (a, b) -> a, () -> new TreeMap<>(new PriceComparator(BUY))));

        String summarySellOrders = ordersRenderer.renderOrders(flatSellOrders);
        String summaryBuyOrders = ordersRenderer.renderOrders(flatBuyOrders);

        return summarySellOrders + "\n\n" + summaryBuyOrders;

    }

    Map<Price, List<Order>> getBuyOrders() {
        return unmodifiableSortedMap(buyOrders);
    }

    Map<Price, List<Order>> getSellOrders() {
        return unmodifiableSortedMap(sellOrders);
    }

    private Map<Price, List<Order>> getOrdersByOrderType(OrderType orderType) {
        Map<Price, List<Order>> orders;
        if (orderType == BUY)
            orders = buyOrders;
        else
            orders = sellOrders;
        return orders;
    }
}
