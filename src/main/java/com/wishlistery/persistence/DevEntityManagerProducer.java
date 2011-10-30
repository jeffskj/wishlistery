package com.wishlistery.persistence;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

public class DevEntityManagerProducer {
    @ExtensionManaged
    @Produces
    @PersistenceUnit(name="dev")
    @ConversationScoped
    EntityManagerFactory producerField;
}
