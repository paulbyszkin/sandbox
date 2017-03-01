package com.byszkin.interview;

import java.util.Comparator;

import static com.byszkin.interview.OrderType.BUY;
import static org.apache.commons.lang.Validate.notNull;

public class PriceComparator implements Comparator<Price> {

    private final int side;

    public PriceComparator(OrderType orderType) {

        notNull(orderType);

        if (orderType == BUY) {
            side = -1;
        }else {
            side = 1;
        }
    }

    @Override
    public int compare(Price p1, Price p2) {
        int compared = p1.amount.compareTo(p2.amount);
        if (compared != 0)
            return compared * side;
        else return p1.currency.toString().compareTo(p2.currency.toString());
    }

}
