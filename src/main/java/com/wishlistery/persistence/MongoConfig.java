package com.wishlistery.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.DBPort;
import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
public class MongoConfig {

	private static final String HOST_VAR = "OPENSHIFT_MONGODB_DB_HOST";
	private static final String PORT_VAR = "OPENSHIFT_MONGODB_DB_PORT";

    public @Bean MongoOperations mongoTemplate(Mongo mongo) {
		MongoTemplate mongoTemplate = new MongoTemplate(mongo, "wishlistery");
		return mongoTemplate;
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
