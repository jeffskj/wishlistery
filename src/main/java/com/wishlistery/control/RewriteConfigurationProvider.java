package com.wishlistery.control;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class RewriteConfigurationProvider extends HttpConfigurationProvider {
    @Override
    public Configuration getConfiguration(ServletContext context)
    {
       return ConfigurationBuilder.begin()
               .addRule(Join.path("/wishlist/{id}").to("/wishlist/{id}.go"))
          
       ;
    }

    @Override
    public int priority()
    {
       return 0;
    }
}
