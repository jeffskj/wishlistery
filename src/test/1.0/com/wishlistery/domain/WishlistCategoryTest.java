package com.wishlistery.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WishlistCategoryTest {

    @Test
    public void testIsDefault() {
        WishlistCategory cat = new WishlistCategory();
        assertFalse(cat.isDefault());
        cat.setName("blah");
        assertFalse(cat.isDefault());
        cat.setName(WishlistCategory.DEFAULT);
        assertTrue(cat.isDefault());
    }

}
