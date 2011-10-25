package com.jeffskj.wishlist.view;

import java.io.IOException;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.transaction.Transactional;

import com.google.common.collect.Lists;
import com.jeffskj.wishlist.domain.Wishlist;
import com.jeffskj.wishlist.domain.WishlistCategory;
import com.jeffskj.wishlist.domain.WishlistItem;
import com.jeffskj.wishlist.domain.WishlistView;


@Named
@Transactional
@ViewScoped
public class WishlistBean extends BaseBean<Wishlist> {
    private static final long serialVersionUID = 1L;

    private WishlistItem item = new WishlistItem();
    private WishlistCategory category = new WishlistCategory();
    private WishlistView view = new WishlistView();
    
    @Inject
    Messages messages;
    
    @Override
    protected Wishlist newEntityInstance() {
        Wishlist wishlist = new Wishlist();
        WishlistCategory cat = new WishlistCategory(WishlistCategory.DEFAULT);
        wishlist.setCategories(Lists.newArrayList(cat));
        cat.setWishlist(wishlist);
        return wishlist;
    }

    public String createView() throws IOException {
        view.setWishlist(entity);
        em.persist(view);
        entity.getViews().add(view);
        return "wishlistView?faces-redirect=true&id=" + view.getId();
    }
    
    public void addItem() {
        for (WishlistCategory cat : entity.getCategories()) {
            if (cat.equals(item.getCategory())) {
                cat.getItems().add(item);
                break;
            }
        }
        save();
        item = new WishlistItem();
    }

    public void removeItem() {
        for (WishlistCategory cat : entity.getCategories()) {
            if (cat.equals(item.getCategory())) {
                cat.getItems().remove(item);
            }
        }
        save();
        item = new WishlistItem();
    }
    
    public void updateItem() {
        for (WishlistCategory cat : entity.getCategories()) {
            if (cat.equals(item.getCategory())) {
                for (WishlistItem it : cat.getItems()) {
                    if (it.equals(item)) {
                        it.setCategory(item.getCategory());
                        it.setDescription(item.getDescription());
                        it.setLink(item.getLink());
                        it.setTitle(item.getTitle());
                    }
                }
            }
        }
        save();
        item = new WishlistItem();
    }
    
    public void addCategory() {
        entity.getCategories().add(category);
        category.setWishlist(entity);
        save();
        category = new WishlistCategory();
    }
    
    public void removeCategory() {
        if (!category.getItems().isEmpty()) {
            messages.error("cannot remove non-empty category");
            return;
        }
        entity.getCategories().remove(category);
        save();
        category = new WishlistCategory();
    }
    
    public void updateCategory() {
        for (WishlistCategory cat : entity.getCategories()) {
            if (cat.equals(category)) {
                cat.setName(category.getName());
            }
        }
        save();
        category = new WishlistCategory();
    }
    
    public WishlistItem getItem() {
        return item;
    }


    public void setItem(WishlistItem item) {
        this.item = item;
    }


    public WishlistCategory getCategory() {
        return category;
    }


    public void setCategory(WishlistCategory category) {
        this.category = category;
    }


    public WishlistView getView() {
        return view;
    }


    public void setView(WishlistView view) {
        this.view = view;
    }
}
