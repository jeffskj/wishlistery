package com.wishlistery.security;

import java.util.Properties;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.jboss.seam.security.external.openid.api.OpenIdRelyingPartyConfigurationApi;
import org.jboss.solder.resourceLoader.Resource;
import org.jboss.solder.servlet.event.Initialized;


public class OpenIdRelyingPartyCustomizer {
    @Inject
    @Resource("open-id.properties")
    private Properties properties;
    
    public void servletInitialized(@Observes @Initialized final ServletContext context, OpenIdRelyingPartyConfigurationApi op) {
        op.setHostName(properties.getProperty("host", "localhost"));
        op.setPort(Integer.parseInt(properties.getProperty("port", "8080")));
        op.setProtocol("http");
    }
}
