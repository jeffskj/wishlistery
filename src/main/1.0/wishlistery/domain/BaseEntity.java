package com.wishlistery.domain;

public abstract class BaseEntity {
    public abstract long getId();

    public boolean isPersisted() {
        return getId() > 0;
    }
    
    @Override
    public int hashCode() {
        return (int) (getId() * 31);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass())  { return false; }
        
        BaseEntity other = (BaseEntity) obj;
        return other.getId() == getId();
    }
    
        
}
