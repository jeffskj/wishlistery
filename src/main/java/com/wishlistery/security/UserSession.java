package com.wishlistery.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.external.openid.OpenIdUser;
import org.jboss.seam.transaction.Transactional;

import com.wishlistery.domain.User;

@Named
@SessionScoped
public class UserSession implements Serializable {
    private static final long serialVersionUID = -685323729171936416L;

    private User user;
    
    @Inject
    Identity identity;
    
    @Inject 
    EntityManager em;
    
    @Produces @Named("currentUser")
    public User getUser() {
        return user;
    }
    
    public boolean isLoggedIn() {
        return identity.isLoggedIn();
    }
    
    @Transactional
    public String load() {
        if (!isLoggedIn()) {
            return "pretty:home";
        }
        if (user != null) {
            return null;
        }
        try {
            user = em.createQuery("from User where openId = :id", User.class)
                    .setParameter("id", identity.getUser().getId()).getSingleResult();
        } catch (NoResultException e) {
            user = new User((OpenIdUser) identity.getUser());
            em.persist(user);
        }
        return null;
    }
}
