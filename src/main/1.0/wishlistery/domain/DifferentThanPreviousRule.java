package com.wishlistery.domain;

import java.util.Map;

public class DifferentThanPreviousRule implements Rule {

    private final Map<User, User> previousMappings;

    public DifferentThanPreviousRule(GiftExchange exchange) {
        previousMappings = exchange.getUserMappingsMap();
    }

    @Override
    public boolean isInvalidPair(User from, User to) {
        return previousMappings.containsKey(from) && previousMappings.get(from).equals(to);
    }
}
