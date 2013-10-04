package com.wishlistery.rs;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.NoLogWebApplicationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wishlistery.domain.Wishlist;
import com.wishlistery.persistence.WishlistRepository;

@Scope("prototype")
@Component
@Path("/wishlist")
public class WishlistWebService {
            
    @Inject
    WishlistRepository wishlistRepo;
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wishlist getWishlistById(@PathParam("id") String id) {
        return wishlistRepo.findOne(id);
    }
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateWishlistById(@PathParam("id") String id, Wishlist updated) {
        Wishlist wishlist = wishlistRepo.findOne(id);
        wishlist.setName(updated.getName());
        wishlist.setDescription(updated.getDescription());
        wishlistRepo.save(wishlist);
    }
    
    @POST
    @Path("{id}/view/{viewName}")
    public void addView(@PathParam("id") String id, @PathParam("viewName") String viewName) {
        Wishlist wishlist = wishlistRepo.findOne(id);
        wishlist.addView(viewName);
        wishlistRepo.save(wishlist);
    }
    
    @DELETE
    @Path("{id}/view/{viewName}")
    public void removeView(@PathParam("id") String id, @PathParam("viewName") String viewName) {
        Wishlist wishlist = wishlistRepo.findOne(id);
        try {
            wishlist.removeView(viewName);
        } catch (IllegalStateException e) {
            throwError(Status.BAD_REQUEST, e.getMessage());
        }
        wishlistRepo.save(wishlist);
    }
    
    @POST
    @Path("{id}/category/{categoryName}")
    public void addCategory(@PathParam("id") String id, @PathParam("categoryName") String categoryName) {
        Wishlist wishlist = wishlistRepo.findOne(id);
        wishlist.addCategory(categoryName);
        wishlistRepo.save(wishlist);
    }
    
    @DELETE
    @Path("{id}/category/{categoryName}")
    public void removeCategory(@PathParam("id") String id, @PathParam("categoryName") String categoryName) {
        Wishlist wishlist = wishlistRepo.findOne(id);
        try {
            wishlist.removeCategory(categoryName);
        } catch (IllegalStateException e) {
            throwError(Status.BAD_REQUEST, e.getMessage());
        }
        wishlistRepo.save(wishlist);
    }
    
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWishlist(Wishlist wishlist) {
        wishlist.setId(null); //ensure new
        wishlist.setCategories(null); //must be managed separately
        wishlist.setViews(null); //must be managed separately
        wishlistRepo.save(wishlist);
        return Response.noContent().location(URI.create("/wishlist/" + wishlist.getId())).build();
    }
    
    private void throwError(Status status, String message) {
        throw new NoLogWebApplicationException(Response.status(status).entity(new ErrorResponse(message)).build());
    }
}
