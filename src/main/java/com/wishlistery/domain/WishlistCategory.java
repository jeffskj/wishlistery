package com.wishlistery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class WishlistCategory extends BaseEntity implements Serializable {
    public static final String DEFAULT = "(none)";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id = 0;

    @JoinColumn
    @ManyToOne(cascade=CascadeType.PERSIST, optional=false)
    private Wishlist wishlist;
    private String name;
    
    @Version
    private int version = 0;

    public WishlistCategory() {
    }
    
    public WishlistCategory(String name) {
        this.name = name;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WishlistItem> getItems() {
        List<WishlistItem> filtered = new ArrayList<WishlistItem>(wishlist.getItems());
        for (Iterator<WishlistItem> it = filtered.iterator(); it.hasNext();) {
            if (!it.next().getCategory().equals(this)) {
                it.remove();
            }
        }
        return filtered;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }
    
    public boolean isDefault() {
        return name != null && name.equals(DEFAULT);
    }
}