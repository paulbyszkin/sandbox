package com.byszkin.interview;

import java.math.BigDecimal;

import static org.apache.commons.lang.Validate.notNull;

public class OrderQuantity {

    public final BigDecimal quantity;
    public final Unit unit;


    public OrderQuantity(BigDecimal quantity, Unit unit) {

        notNull(quantity);
        notNull(unit);

        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderQuantity that = (OrderQuantity) o;

        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        return unit == that.unit;
    }

    @Override
    public int hashCode() {
        int result = quantity != null ? quantity.hashCode() : 0;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
