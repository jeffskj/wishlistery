package com.wishlistery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@JsonIgnoreProperties({"blank", "persisted"})
public class Wishlist extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id    
    private String id;
    
    private String name;
    private String description;
        
    private List<WishlistItem> items = new ArrayList<>();
    private List<String> views = new ArrayList<>(); //list of view names, actual assignment is done as attribute of item
    private List<String> categories = new ArrayList<>(); //list of category names, actual assignment is done as attribute of item
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getViews() {
        return views;
    }

    public void setViews(List<String> views) {
        this.views = views;
    }
    
    public boolean isBlank() {
        return items == null || items.isEmpty();
    }

    public List<WishlistItem> getItems() {
        return items;
    }

    public void addItem(WishlistItem item) {
        item.setId(nextId());
        items.add(item);
    }
    
    public void removeItem(WishlistItem item) {
        items.remove(item);
    }
    
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<WishlistItem> getItemsInCategory(String name) {
        return getItemsInCategory(items, name);
    }
    
    public List<WishlistItem> getItemsInViewAndCategory(String view, String category) {
        return getItemsInCategory(getItemsInView(view), name);
    }
    
    public List<WishlistItem> getItemsInCategory(List<WishlistItem> itemList, String category) {
        List<WishlistItem> results = new ArrayList<>();
        for (WishlistItem item : itemList) {
            if (item.getCategory().equals(category)) { 
                results.add(item);
            }
        }
        return results;
    }
    
    public List<WishlistItem> getItemsInView(String view) {
        List<WishlistItem> results = new ArrayList<>();
        for (WishlistItem item : items) {
            if (item.getViews().contains(view)) { 
                results.add(item);
            }
        }
        return results;
    }

    private int nextId() {
        int max = 0;
        for (WishlistItem item : items) {
            if (item.getId() > max) { max = item.getId(); }
        }
        return max + 1;
    }

    public void addView(String viewName) {
        if (!views.contains(viewName)) {
            views.add(viewName);
        }
    }

    public void removeView(String viewName) {
        if (!getItemsInView(viewName).isEmpty()) {
            throw new IllegalStateException("cannot remove a view while items still belong to it");
        }
        views.remove(viewName);        
    }
    
    public void addCategory(String categoryName) {
        if (!categories.contains(categoryName)) {
            categories.add(categoryName);
        }
    }

    public void removeCategory(String categoryName) {
        if (!getItemsInCategory(categoryName).isEmpty()) {
            throw new IllegalStateException("cannot remove a category while items still belong to it");
        }
        categories.remove(categoryName);        
    }
}
