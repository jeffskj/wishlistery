package com.jeffskj.wishlist.domain;

import java.io.Serializable;

import org.jboss.seam.security.external.openid.OpenIdUser;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final OpenIdUser openIdUser;

    public User(OpenIdUser openIdUser) {
        this.openIdUser = openIdUser;
    }
    
    public String getId() {
        return openIdUser.getId();
    }

    public String getFirstName() {
        return openIdUser.getAttribute("firstName");
    }
    
    public String getEmail() {
        return openIdUser.getAttribute("email");
    }

    public String getLastName() {
        return openIdUser.getAttribute("lastName");
    }
}