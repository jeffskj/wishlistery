package com.wishlistery.domain;

public abstract class BaseEntity {
    public abstract String getId();

    public boolean isPersisted() {
        return getId() != null;
    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
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
