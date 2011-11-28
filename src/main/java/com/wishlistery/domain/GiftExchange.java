package com.wishlistery.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GiftExchange extends BaseEntity {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String label;
    
    @ManyToOne
    @JoinColumn
	private GiftExchangeDefinition definition;
    
    @ElementCollection
	private List<UserMapping> userMapping = new ArrayList<UserMapping>();

	public GiftExchangeDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(GiftExchangeDefinition definition) {
		this.definition = definition;
	}

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<User, User> getUserMappingsMap() {
        Map<User, User> mapping = new HashMap<User, User>();
        for (UserMapping m : userMapping) {
            mapping.put(m.getFromUser(), m.getToUser());
        }
        return mapping;
    }

    public List<UserMapping> getUserMapping() {
        return userMapping;
    }

    public void setUserMapping(List<UserMapping> userMapping) {
        this.userMapping = userMapping;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
	
	
}
