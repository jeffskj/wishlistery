package com.wishlistery.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.jboss.seam.transaction.Transactional;
import org.joda.time.DateTime;

import com.wishlistery.domain.GiftExchange;
import com.wishlistery.domain.GiftExchangeDefinition;
import com.wishlistery.domain.IllegalPairRule;
import com.wishlistery.domain.User;
import com.wishlistery.domain.UserMapping;

@Named
@Transactional
@ViewScoped
public class GiftExchangeBean extends BaseBean<GiftExchangeDefinition> implements Serializable {
    private static final long serialVersionUID = 6759856282432275656L;

    private User newParticipant;
    private User illegalPairFrom;
    private User illegalPairTo;
    private String exchangeLabel = getCurrentYear(); 
    private GiftExchange selectedExchange;
    
    
    @Override
    protected GiftExchangeDefinition newEntityInstance() {
        return new GiftExchangeDefinition();
    }
    
    @Override
    protected void loadAssociations() {
        for (GiftExchange ex : entity.getExchanges()) {
            ex.getUserMapping().size(); 
        }
    }
    
    public void addNewParticipant() {
        entity.getParticipants().add(newParticipant);
        save();
    }
    
    public void removeParticipant(User participant) {
        entity.getParticipants().remove(participant);
        save();
    }
    
    public void addIllegalPair() {
        entity.getIllegalPairs().add(new IllegalPairRule(illegalPairFrom, illegalPairTo));
        entity.getIllegalPairs().add(new IllegalPairRule(illegalPairTo, illegalPairFrom));
        save();
    }
    
    public void removeIllegalPair(IllegalPairRule rule) {
        entity.getIllegalPairs().remove(rule);
        save();
    }
    
    public void createExchange() {
        if (entity.getExchanges() == null) {
            entity.setExchanges(new ArrayList<GiftExchange>());
        }
        
        GiftExchange exchange = entity.createExchange();
        exchange.setLabel(exchangeLabel);
        entity.getExchanges().add(exchange);
        save();
    }
    
    public void setNewParticipant(User newParticipant) {
        this.newParticipant = newParticipant;
    }
    
    public User getNewParticipant() {
        return newParticipant; 
    }

    public User getIllegalPairTo() {
        return illegalPairTo;
    }

    public void setIllegalPairTo(User illegalPairTo) {
        this.illegalPairTo = illegalPairTo;
    }

    public User getIllegalPairFrom() {
        return illegalPairFrom;
    }

    public void setIllegalPairFrom(User illegalPairFrom) {
        this.illegalPairFrom = illegalPairFrom;
    }
    
    public GiftExchange getSelectedExchange() {
        return selectedExchange;
    }

    public List<UserMapping> getSelectedExchangeEntries() {
        return selectedExchange != null ? selectedExchange.getUserMapping() : null;
    }
    
    public void setSelectedExchange(GiftExchange selectedExchange) {
        this.selectedExchange = selectedExchange;
    }

    public String getExchangeLabel() {
        return exchangeLabel;
    }

    public void setExchangeLabel(String exchangeLabel) {
        this.exchangeLabel = exchangeLabel;
    }
    
    private static String getCurrentYear() {
        return String.valueOf(new DateTime().getYear());
    }
}
