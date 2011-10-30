package com.wishlistery.persistence;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

@Alternative
public class ProdEntityManagerProducer {
    @ExtensionManaged
    @Produces
    @PersistenceUnit(name="prod")
    @ConversationScoped
    EntityManagerFactory producerField;
}
