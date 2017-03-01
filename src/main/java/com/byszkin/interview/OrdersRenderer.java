package com.byszkin.interview;

import java.util.Map;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang.Validate.notNull;

public class OrdersRenderer {

    public String renderOrders(Map<Price, OrderQuantity> data) {

        notNull(data);

        return data.entrySet().stream().map(e-> "- " + e.getValue().quantity + " " + e.getValue().unit + " for " + e.getKey().currency.getSymbol() + e.getKey().amount).collect(joining("\n"));

    }
}
