package com.wishlistery.rs;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import com.wishlistery.domain.Wishlist;
import com.wishlistery.domain.WishlistItem;

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
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        
        System.out.println(response.getLocation().getHref());
        System.out.println(response.getEntity(String.class));
        response = response.getLocation().request().get(Wishlist.class);
        assertEquals(200, response.getStatus());
        
        Wishlist entity = response.getEntity(Wishlist.class);
        assertEquals(name, entity.getName());
        System.out.println(entity.getId());
        
        request = new ClientRequest(BASE_URI + "/{id}/view/testview").pathParameter("id", entity.getId());
        response = request.post();
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        System.out.println(response.getEntity(String.class));
        
        response = request.delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
        
        request = new ClientRequest(BASE_URI + "/{id}/category/SomeCategory").pathParameter("id", entity.getId());
        response = request.post();
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        System.out.println(response.getEntity(String.class));
        
        response = request.delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
        
        request = new ClientRequest(BASE_URI + "/{id}/item").pathParameter("id", entity.getId());
        WishlistItem item = new WishlistItem("my item", "my description", "http://somelink.com");
        request.body(MediaType.APPLICATION_JSON_TYPE, item);
        response = request.post();
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        System.out.println(response.getEntity(String.class));
        
        item.setDescription("updated description");
        request = new ClientRequest(BASE_URI + "/{id}/item/{itemId}").pathParameter("id", entity.getId()).pathParameter("itemId", 1);
        request.body(MediaType.APPLICATION_JSON_TYPE, item);
        response = request.put();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
        System.out.println(response.getEntity(String.class));
        
        request = new ClientRequest(BASE_URI + "/{id}/item/{itemId}").pathParameter("id", entity.getId()).pathParameter("itemId", 1);
        response = request.delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
        
        request = new ClientRequest(BASE_URI + "/{id}").pathParameter("id", entity.getId());
        response = request.delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

}
