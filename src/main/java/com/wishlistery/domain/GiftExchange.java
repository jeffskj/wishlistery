package com.wishlistery.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GiftExchange extends BaseEntity {
    
    private String id;
    
    private String label;
    
	private GiftExchangeDefinition definition;
    
	private List<UserMapping> userMapping = new ArrayList<UserMapping>();

	public GiftExchangeDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(GiftExchangeDefinition definition) {
		this.definition = definition;
	}

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
