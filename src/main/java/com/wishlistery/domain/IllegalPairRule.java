package com.wishlistery.domain;


public class IllegalPairRule implements Rule {
    
    private User from;
    private User to;
    
    
	public IllegalPairRule() {}
	
	public IllegalPairRule(User from, User to) {
        this.from = from;
        this.to = to;
	}
	
	public boolean isInvalidPair(User from, User to) {
		return this.from.equals(from) && this.to.equals(to);
	}

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
}
