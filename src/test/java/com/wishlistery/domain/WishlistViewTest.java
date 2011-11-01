package com.wishlistery.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WishlistViewTest {

    @Test
    public void testIsCategoryEmpty() {
        WishlistCategory[] categories = new WishlistCategory[] {
            makeCategory(1), makeCategory(2), makeCategory(3)
        };
        
        WishlistItem[] items = new WishlistItem[] {
            makeWishlistItem(1, categories[0]),
            makeWishlistItem(2, categories[1]),
            makeWishlistItem(3, categories[1]),
        };
        
        List<WishlistItem> itemList = Arrays.asList(items);
        
        WishlistView view = new WishlistView();
        view.setId(1);
        view.setItems(itemList);
        assertFalse(view.isCategoryEmpty(categories[0]));
        assertFalse(view.isCategoryEmpty(categories[1]));
        assertTrue(view.isCategoryEmpty(categories[2]));
        
        view.setItems(itemList.subList(1, 2));
        assertTrue(view.isCategoryEmpty(categories[0]));
        assertFalse(view.isCategoryEmpty(categories[1]));
        assertTrue(view.isCategoryEmpty(categories[2]));

    }

    private WishlistCategory makeCategory(int i) {
        WishlistCategory cat = new WishlistCategory();
        cat.setId(i);
        cat.setName("category " + i);
        return cat;
    }

    private WishlistItem makeWishlistItem(int i, WishlistCategory cat) {
        WishlistItem item = new WishlistItem();
        item.setId(i);
        item.setTitle("item " + i);
        item.setCategory(cat);
        cat.getItems().add(item);
        return item;
    }

}
