package com.wishlistery.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.DBPort;
import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
public class MongoConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);
    
	private static final String HOST_VAR = "OPENSHIFT_MONGODB_DB_HOST";
	private static final String PORT_VAR = "OPENSHIFT_MONGODB_DB_PORT";
	private static final String USER_VAR = "OPENSHIFT_MONGODB_DB_USERNAME";
	private static final String PASSWORD_VAR = "OPENSHIFT_MONGODB_DB_PASSWORD";
	

    public @Bean MongoOperations mongoTemplate(Mongo mongo) {
		return new MongoTemplate(mongo, "app", getCredentials());
	}

    private UserCredentials getCredentials() {
        if (System.getenv(USER_VAR) != null && System.getenv(PASSWORD_VAR) != null) {
            logger.info("creating mongo template with user {}", System.getenv(USER_VAR));
            return new UserCredentials(System.getenv(USER_VAR), System.getenv(PASSWORD_VAR));
        } else {
            logger.info("creating mongo template with no credentials");
            return null;
        }
    }

	/*
	 * Factory bean that creates the Mongo instance
	 */
	public @Bean MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost(System.getenv(HOST_VAR) != null ? System.getenv(HOST_VAR) : "localhost");
		mongo.setPort(System.getenv(PORT_VAR) != null ? Integer.parseInt(System.getenv(PORT_VAR)) : DBPort.PORT);
		return mongo;
	}

	/*
	 * Use this post processor to translate any MongoExceptions thrown in @Repository annotated classes
	 */
	public @Bean PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
