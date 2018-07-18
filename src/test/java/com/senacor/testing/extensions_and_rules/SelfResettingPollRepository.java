package com.senacor.testing.extensions_and_rules;

import com.senacor.testing.PollRepository;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Profile("test")
public class SelfResettingPollRepository extends PollRepository implements BeforeEachCallback, AfterEachCallback {

    @Inject
    public SelfResettingPollRepository(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        this.deleteAll();

    }

    @Override
    public void beforeEach(ExtensionContext context) {
        this.deleteAll();

    }
}