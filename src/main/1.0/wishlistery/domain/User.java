package com.wishlistery.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jboss.seam.security.external.openid.OpenIdUser;

@Table(name="WishlistUser")
@Entity
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private long id;
    private String openId;
    private String email;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy="user")
    private List<Wishlist> wishlists;
    
    public User() {}

    public User(OpenIdUser openIdUser) {
        openId = openIdUser.getId();
        firstName = openIdUser.getAttribute("firstName");
        lastName = openIdUser.getAttribute("lastName");
        email = openIdUser.getAttribute("email");
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
    
    @Override
    public String toString() {
        return "[" + firstName + "," + lastName + "]";
    }
}