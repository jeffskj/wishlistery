package com.wishlistery.domain;


public class UserMapping {
    
    private User fromUser;
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