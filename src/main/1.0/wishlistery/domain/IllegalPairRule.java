package com.wishlistery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IllegalPairRule extends BaseEntity implements Rule {
    
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn
    private User from;
    
    @ManyToOne
    @JoinColumn
    private User to;
    
    
	public IllegalPairRule() {}
	
	public IllegalPairRule(User from, User to) {
        this.from = from;
        this.to = to;
	}
	
	public boolean isInvalidPair(User from, User to) {
		return this.from.equals(from) && this.to.equals(to);
	}

    @Override
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
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
