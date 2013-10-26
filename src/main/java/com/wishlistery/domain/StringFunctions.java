package com.wishlistery.domain;

import com.google.common.base.Function;

public class StringFunctions {
    public static Function<String, String> trim() {
        return new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input != null ? input.trim() : input;
            }
        };
    }
}
