package com.wishlistery.control;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.wishlistery.domain.Wishlist;
import com.wishlistery.persistence.WishlistRepository;

@Controller
public class WishlistController {
    @Inject
    WishlistRepository wishlistRepo;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @RequestMapping("/wishlist/{id}")
    public ModelAndView getWishlist(@PathVariable String id) throws JsonGenerationException, JsonMappingException, IOException {
        Wishlist wishlist = wishlistRepo.findOne(id);
        String json = mapper.writeValueAsString(wishlist);
        json = StringEscapeUtils.escapeEcmaScript(json);
        return new ModelAndView("wishlist", ImmutableMap.of("wishlist", wishlist, "wishlistJson", json));
    }
}
