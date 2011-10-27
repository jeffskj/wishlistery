package com.wishlistery.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class WishlistItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id = 0;

    @Version
    private int version = 0;

    @JoinColumn
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})    
    private WishlistCategory category;
    
    @ManyToMany
    private List<WishlistView> views;
    
    private String title;
    private String description;
    private String link;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }
    
    public String getTrimmedLink() {
        if (link == null) {
            return null;
        }
        String trimmed = link.replaceFirst("https?://", "");
        if (trimmed.length() > 65) {
            trimmed = trimmed.substring(0, 47) + "..." + trimmed.substring(trimmed.length()-15); 
        }
        return trimmed;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public WishlistCategory getCategory() {
        return category;
    }

    public void setCategory(WishlistCategory category) {
        this.category = category;
    }

    public List<WishlistView> getViews() {
        return views;
    }

    public void setViews(List<WishlistView> views) {
        this.views = views;
    }
}
