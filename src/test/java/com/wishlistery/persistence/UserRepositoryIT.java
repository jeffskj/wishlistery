package com.wishlistery.persistence;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wishlistery.domain.User;
import com.wishlistery.domain.Wishlist;
import com.wishlistery.domain.WishlistItem;

public class UserRepositoryIT {

    @Test
    public void canPerformCrudOnUser() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/bootstrap.xml");
        UserRepository userRepository = context.getBean(UserRepository.class);
        WishlistRepository listRepository = context.getBean(WishlistRepository.class);
        userRepository.deleteAll();
        listRepository.deleteAll();
        
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Test Wishlist");
        wishlist.addItem(new WishlistItem("some item", "my description", "http://google.com"));
        listRepository.save(wishlist);
        System.out.println(wishlist.getId());
        
        User user = new User();
        user.setFirstName("Jeff");
        user.setLastName("Skjonsby");
        user.setWishlists(Arrays.asList(wishlist));
        userRepository.save(user);
        
        user = userRepository.findOne(user.getId());
        System.out.println(user.getWishlists().get(0).getId());
    }

}
