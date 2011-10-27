package com.wishlistery.security;

import java.io.IOException;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.security.events.DeferredAuthenticationEvent;
import org.jboss.seam.security.external.api.ResponseHolder;
import org.jboss.seam.security.external.openid.OpenIdAuthenticator;
import org.jboss.seam.security.external.openid.api.OpenIdPrincipal;
import org.jboss.seam.security.external.spi.OpenIdRelyingPartySpi;

public class OpenIdRelyingPartySpiImpl implements OpenIdRelyingPartySpi {

   @Inject 
   private ServletContext servletContext;

   @Inject
   private HttpServletRequest request;
   
   @Inject 
   OpenIdAuthenticator openIdAuthenticator;   

   @Inject 
   Event<DeferredAuthenticationEvent> deferredAuthentication;

   @Inject
   UserSession userSession;
   
   public void loginSucceeded(OpenIdPrincipal principal, ResponseHolder responseHolder) {
      try {
         openIdAuthenticator.success(principal);
         deferredAuthentication.fire(new DeferredAuthenticationEvent(true));
         request.getSession().setMaxInactiveInterval(3600);
         responseHolder.getResponse().sendRedirect(servletContext.getContextPath() + "/profile");
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void loginFailed(String message, ResponseHolder responseHolder) {
      try {
          responseHolder.getResponse().sendRedirect(servletContext.getContextPath() + "/home");
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}