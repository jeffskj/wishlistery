package com.wishlistery.domain;

import java.io.Serializable;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

public class WishlistItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id = 0;

    private String category;
    private Set<String> views;
    
    private String title;
    private String description;
    private String link;

    public WishlistItem() {
    }
    
    public WishlistItem(String title, String description, String link) {
        super();
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    @JsonIgnore
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getViews() {
        return views;
    }

    public void setViews(Set<String> views) {
        this.views = views;
    }
    
    @Override
    public int hashCode() {
        return getId() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass())  { return false; }
        
        BaseEntity other = (BaseEntity) obj;
        return other.getId().equals(getId());
    }
}