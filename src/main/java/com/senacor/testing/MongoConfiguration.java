package com.senacor.testing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.validation.Validator;

@Configuration
@EnableMongoRepositories(basePackages = "com.senacor.testing")
public class MongoConfiguration {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator validator) {
        return new ValidatingMongoEventListener(validator);
    }

}