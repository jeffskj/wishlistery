package com.wishlistery.persistence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.SetSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Sets;
import com.wishlistery.domain.Wishlist;
import com.wishlistery.domain.WishlistItem;

public class WishlistRepositoryIT {

    @Test
    public void canPerformCrudOnWishlist() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/bootstrap.xml");
        WishlistRepository repository = context.getBean(WishlistRepository.class);
        repository.deleteAll();
        
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Test Wishlist");
        wishlist.setViews(Arrays.asList("view1", "view2"));
        wishlist.setCategories(Arrays.asList("cat1", "cat2"));
        wishlist.addItem(new WishlistItem("some item", "my description", "http://google.com"));
        repository.save(wishlist);
        List<Wishlist> lists = repository.findAll();
        assertEquals(1, lists.size());
        for (Wishlist list : lists) {
            System.out.println(list.getId() + " " + list.getName());            
            System.out.println(list.getCategories());
            System.out.println(list.getViews());
            for (WishlistItem item : list.getItems()) {
                System.out.println(" - " + item.getTitle());                
            }
        }
    }

}
