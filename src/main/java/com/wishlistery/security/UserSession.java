package com.wishlistery.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.external.openid.OpenIdUser;

import com.wishlistery.domain.User;

@Named
@SessionScoped
public class UserSession implements Serializable {
    private static final long serialVersionUID = -685323729171936416L;

    @Inject
    Identity identity;
    
    @Produces @Named("currentUser")
    public User getUser() {
        return new User((OpenIdUser) identity.getUser());
    }
    
    public String checkLoggedIn() {
        return isLoggedIn() ? null : "pretty:home";
    }
    
    public boolean isLoggedIn() {
        return identity.isLoggedIn();
    }
}
