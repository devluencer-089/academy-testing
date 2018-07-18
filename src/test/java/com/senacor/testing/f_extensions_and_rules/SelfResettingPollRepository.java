package com.senacor.testing.f_extensions_and_rules;

import com.senacor.testing.PollRepository;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Profile("test")
public class SelfResettingPollRepository extends PollRepository implements BeforeEachCallback, AfterEachCallback {

    private static final Logger logger = LoggerFactory.getLogger(SelfResettingPollRepository.class);

    @Inject
    public SelfResettingPollRepository(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        logger.info("deleting all polls");
        this.deleteAll();

    }

    @Override
    public void beforeEach(ExtensionContext context) {
        logger.info("deleting all polls");
        this.deleteAll();

    }
}