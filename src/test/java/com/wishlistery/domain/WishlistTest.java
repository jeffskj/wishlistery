package com.wishlistery.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class WishlistTest {

    @Test
    public void testIsBlank() {
        Wishlist list = new Wishlist();
        assertTrue(list.isBlank());
        list.setCategories(new ArrayList<WishlistCategory>());
        list.getCategories().add(new WishlistCategory());
        assertTrue(list.isBlank());
        list.getCategories().get(0).setItems(Arrays.asList(new WishlistItem()));
    }

}
