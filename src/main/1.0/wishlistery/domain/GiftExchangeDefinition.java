package com.wishlistery.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
public class GiftExchangeDefinition extends BaseEntity {
    
    private static final Random RANDOM = new Random();

    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    @ManyToOne
    private User organizer;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
	private List<User> participants = Lists.newArrayList();
    
    @OneToMany(mappedBy="definition", cascade=CascadeType.ALL)
	private List<GiftExchange> exchanges = Lists.newArrayList();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<IllegalPairRule> illegalPairs = Lists.newArrayList();
	
    private boolean differentThanPrevious;
	
	@Transient
	private Set<BiMap<User, User>> allMappings;

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public List<GiftExchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<GiftExchange> exchanges) {
		this.exchanges = exchanges;
	}

	private List<Rule> getRules() {
	    List<Rule> rules = new ArrayList<Rule>(illegalPairs);
	    if (differentThanPrevious && !exchanges.isEmpty()) {
	        rules.add(new DifferentThanPreviousRule(exchanges.get(exchanges.size()-1)));
	    }
		return rules;
	}

	public Set<BiMap<User, User>> getAllMappings() {
		calculatePossibleMappings();
		return allMappings;
	}
	
	public void createAllMappings(Set<BiMap<User, User>> mappings, BiMap<User, User> current) {
		if (current.size() == participants.size()) {
			mappings.add(current);
			return;
		}
		
		for (User f : participants) {
			for (User t : participants) {
				if (f == t || current.containsKey(f) || current.containsValue(t) || !passesAllTheRules(f, t)) { continue; }
				createAllMappings(mappings, ImmutableBiMap.<User, User>builder().putAll(current).put(f, t).build());
			}
		}
	}
	
	private boolean passesAllTheRules(User f, User t) {
		for (Rule rule : getRules()) {
			if (rule.isInvalidPair(f, t)) {
				return false;
			}
		}
		return true;
	}
	
	public void calculatePossibleMappings() {
		if (allMappings == null) {
			allMappings = new HashSet<BiMap<User,User>>();
			createAllMappings(allMappings, ImmutableBiMap.<User, User>of());
		}
	}
	
	public GiftExchange createExchange() {
		GiftExchange ex = new GiftExchange();
		ex.setDefinition(this);
		ex.setUserMapping(new ArrayList<UserMapping>());
		Set<Entry<User, User>> mapping = Iterables.get(getAllMappings(), RANDOM.nextInt(allMappings.size()-1)).entrySet();
        for (Entry<User, User> e : mapping) {
		    ex.getUserMapping().add(new UserMapping(e.getKey(), e.getValue()));
		}
		
		return ex;
	}

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public boolean isDifferentThanPrevious() {
        return differentThanPrevious;
    }

    public void setDifferentThanPrevious(boolean differentThanLastExchange) {
        differentThanPrevious = differentThanLastExchange;
        allMappings = null; // invalidate cache
    }

    public List<IllegalPairRule> getIllegalPairs() {
        return illegalPairs;
    }

    public void setIllegalPairs(List<IllegalPairRule> illegalPairs) {
        this.illegalPairs = illegalPairs;
        allMappings = null; // invalidate cache
    }
}
