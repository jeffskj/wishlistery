package com.wishlistery.rs;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import com.wishlistery.domain.Wishlist;

public class WishlistWebServiceIT {

    private static final String BASE_URI = "http://localhost:8080/rs/wishlist";
    
    @Test
    public void doCrudOperationsOnWishlist() throws Exception {
        ClientRequest request = new ClientRequest(BASE_URI);        
        String name = "new test list" + System.currentTimeMillis();
        
        Wishlist list = new Wishlist();
        list.setName(name);
        list.setDescription("this is a test wishlist");
        request.body(MediaType.APPLICATION_JSON_TYPE, list);
        ClientResponse<?> response = request.post();
        assertEquals(204, response.getStatus());
        
        System.out.println(response.getLocation().getHref());
        
        response = response.getLocation().request().get(Wishlist.class);
        assertEquals(200, response.getStatus());
        
        Wishlist entity = response.getEntity(Wishlist.class);
        assertEquals(name, entity.getName());
        System.out.println(entity.getId());
        
        request = new ClientRequest(BASE_URI + "/{id}/view/testview").pathParameter("id", entity.getId());
        response = request.post();
        assertEquals(204, response.getStatus());
        
        response = request.delete();
        assertEquals(204, response.getStatus());
        
        request = new ClientRequest(BASE_URI + "/{id}/category/SomeCategory").pathParameter("id", entity.getId());
        response = request.post();
        assertEquals(204, response.getStatus());
        
        response = request.delete();
        assertEquals(204, response.getStatus());
        
        
        request = new ClientRequest(BASE_URI + "/{id}").pathParameter("id", entity.getId());
        response = request.delete();
        assertEquals(204, response.getStatus());
    }

}
