package com.wishlistery.persistence;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

public class EntityManagerProducer {
    @ExtensionManaged
    @Produces
    @PersistenceUnit(unitName="wishlistery")
    @ConversationScoped
    EntityManagerFactory producerField;
}
