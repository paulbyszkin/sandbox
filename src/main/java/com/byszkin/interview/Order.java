package com.byszkin.interview;

import static org.apache.commons.lang.Validate.notNull;

public class Order {

    public final long userId;
    public final OrderQuantity orderQuantity;
    public final Price pricePerUnit;
    public final OrderType orderType;


    public Order(long userId, OrderQuantity orderQuantity, Price pricePerUnit, OrderType orderType) {

        notNull(orderQuantity);
        notNull(pricePerUnit);
        notNull(orderType);

        this.userId = userId;
        this.orderQuantity = orderQuantity;
        this.pricePerUnit = pricePerUnit;
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (userId != order.userId) return false;
        if (orderQuantity != null ? !orderQuantity.equals(order.orderQuantity) : order.orderQuantity != null)
            return false;
        if (pricePerUnit != null ? !pricePerUnit.equals(order.pricePerUnit) : order.pricePerUnit != null) return false;
        return orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (orderQuantity != null ? orderQuantity.hashCode() : 0);
        result = 31 * result + (pricePerUnit != null ? pricePerUnit.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        return result;
    }
}
