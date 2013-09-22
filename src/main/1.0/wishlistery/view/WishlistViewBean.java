package com.wishlistery.view;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.transaction.Transactional;

import com.wishlistery.domain.WishlistItem;
import com.wishlistery.domain.WishlistView;
import com.wishlistery.security.UserSession;


@Named
@Transactional
@ViewScoped
public class WishlistViewBean extends BaseBean<WishlistView> {
    private static final long serialVersionUID = 1L;

    @Inject
    Messages messages;
    
    @Inject
    UserSession session;
    
    private boolean viewAsOtherUser;
    
    @Override
    protected WishlistView newEntityInstance() {
        return new WishlistView();
    }
    
    public void addItem(WishlistItem item) {
        entity.getItems().add(item);
        save();
    }
    
    public void removeItem(WishlistItem item) {
        entity.getItems().remove(item);
        save();
    }

    public boolean isViewAsOtherUser() {
        return viewAsOtherUser || !isOriginalUser();
    }

    public boolean isOriginalUser() {
        if (session != null && session.isLoggedIn()) {
            return entity.getWishlist().getUser() != null 
                    && entity.getWishlist().getUser().equals(session.getUser());
        }
        return false;
    }
    
    public void setViewAsOtherUser(boolean viewAsOtherUser) {
        this.viewAsOtherUser = viewAsOtherUser;
    }
}
