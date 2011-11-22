package com.wishlistery.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.transaction.Transactional;

import com.wishlistery.domain.User;
import com.wishlistery.domain.Wishlist;

@Named
@Transactional
@ViewScoped
public class SearchBean implements Serializable {
    private static final long serialVersionUID = 6759856282432275656L;
    private String query;
    private List<User> results;

    @Inject
    EntityManager em;
    
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public List<User> getResults() {
        return results;
    }

    public void search() {
        if (query.matches("\\w+\\s+\\w+")) {
            String[] parts = query.split("\\s+");
            results = findByName(parts[0], parts[1]);
        } else {
            results = findByEmail(query);
        }
    }

    public List<Wishlist> getWishlistsForUser(User u) {
        return em.createQuery("from Wishlist where user = :u", Wishlist.class).setParameter("u", u).getResultList();
    }
    
    List<User> findByEmail(String email) {
        return em.createQuery("from User where email = :email", User.class).setParameter("email", email).getResultList();
    }

    List<User> findByName(String firstName, String lastName) {
        return em.createQuery("from User where lower(firstName) = lower(:fname) and lower(lastName) = lower(:lname)", User.class)
                .setParameter("fname", firstName).setParameter("lname", lastName).getResultList();
    }
    
    public String goTo() {
        return "pretty:search";
    }
}
