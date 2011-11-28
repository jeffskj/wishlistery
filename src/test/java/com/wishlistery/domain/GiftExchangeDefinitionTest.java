package com.wishlistery.domain;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.BiMap;

public class GiftExchangeDefinitionTest {
    
    private long nextId = 1;
    
	@Test
	public void testBasicNoRulesExchange() {
		GiftExchangeDefinition def = new GiftExchangeDefinition();
		User joe = makeUser("Joe");
		User bob = makeUser("Bob");
		User sue = makeUser("Sue");
		User ann = makeUser("Ann");
		
		def.setParticipants(Arrays.asList(joe, bob, sue, ann));
		
		IllegalPairRule joeAnn = new IllegalPairRule(joe, ann);
        def.setIllegalPairs(Arrays.asList(joeAnn));
		
		
		Set<BiMap<User, User>> allMappings = def.getAllMappings();
		for (BiMap<User, User> mapping : allMappings) {
			System.out.println(mapping);
			assertFalse(mapping.get(joe) == ann);
		}
		System.out.println("---------------------------------------------");
		
		IllegalPairRule bobJoe = new IllegalPairRule(bob, joe);
		def.setIllegalPairs(Arrays.asList(bobJoe, joeAnn));
		allMappings = def.getAllMappings();
		
		for (BiMap<User, User> mapping : allMappings) {
			System.out.println(mapping);
			assertFalse(mapping.get(joe).equals(ann));
			assertFalse(mapping.get(bob).equals(joe));
		}
	}

    private User makeUser(String name) {
        User u = new User();
        u.setId(nextId++);
        u.setFirstName(name);
        u.setLastName("Test");
        u.setEmail("test@example.com");
        return u;
    }

}
