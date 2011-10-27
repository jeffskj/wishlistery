package com.wishlistery.security;

import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;

import org.jboss.seam.security.external.openid.api.OpenIdRelyingPartyConfigurationApi;
import org.jboss.solder.servlet.event.Initialized;


public class OpenIdRelyingPartyCustomizer {
    public void servletInitialized(@Observes @Initialized final ServletContext context, OpenIdRelyingPartyConfigurationApi op) {
        op.setHostName("localhost");
        op.setPort(8080);
        op.setProtocol("http");
    }
}
