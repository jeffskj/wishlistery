package com.wishlistery.control;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.wishlistery.persistence.WishlistRepository;

@Controller
public class WishlistController {
    @Inject
    WishlistRepository wishlistRepo;
    
    @RequestMapping("/wishlist/{id}")
    public ModelAndView getWishlist(@PathVariable String id) {
        return new ModelAndView("wishlist", ImmutableMap.of("wishlist", wishlistRepo.findOne(id)));
    }
}
