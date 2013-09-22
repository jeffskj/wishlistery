package com.wishlistery.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.transaction.Transactional;

import com.wishlistery.domain.GiftExchange;
import com.wishlistery.domain.GiftExchangeDefinition;
import com.wishlistery.domain.User;
import com.wishlistery.domain.Wishlist;

@Named
@Transactional
@ViewScoped
public class UserBean implements Serializable {
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
        results = find(query);
    }

    public List<User> find(String query) {
        if (query.matches("\\w+\\s+\\w+")) {
            String[] parts = query.split("\\s+");
            return findByName(parts[0], parts[1]);
        } else {
            return findByEmail(query);
        }
    }

    public List<GiftExchangeDefinition> getExchangeDefinitionsForUser(User u) {
        return em.createQuery("from GiftExchangeDefinition where organizer = :user", GiftExchangeDefinition.class)
                .setParameter("user", u).getResultList();
    }
    
    public List<GiftExchange> getExchangeParticipationForUser(User u) {
        return em.createQuery("from GiftExchange e join e.userMapping m where m.fromUser = :user", GiftExchange.class)
                .setParameter("user", u).getResultList();
    }
    
    public List<Wishlist> getWishlistsForUser(User u) {
        return em.createQuery("from Wishlist where user = :u", Wishlist.class).setParameter("u", u).getResultList();
    }
    
    List<User> findByEmail(String email) {
        return em.createQuery("from User where email = :email", User.class).setParameter("email", email).getResultList();
    }

    List<User> findByName(String firstName, String lastName) {
        if (firstName.length() < 3 || lastName.length() < 3) {
            return new ArrayList<User>();
        }
        return em.createQuery("from User where lower(firstName) like :fname and lower(lastName) like :lname", User.class)
                .setParameter("fname", firstName.toLowerCase() + "%")
                .setParameter("lname", lastName.toLowerCase() + "%")
                .getResultList();
    }
    
    public String goTo() {
        return "pretty:search";
    }
}
