package com.wishlistery.domain;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class UserMapping {
    
    @OneToOne
    private User fromUser;
    
    @OneToOne
    private User toUser;

    public UserMapping() {}
    
    public UserMapping(User from, User to) {
        this.fromUser = from;
        this.toUser = to;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User from) {
        this.fromUser = from;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User to) {
        this.toUser = to;
    }
}