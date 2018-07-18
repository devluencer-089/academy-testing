package com.senacor.testing.f_extensions_and_rules;


import com.senacor.testing.Application;
import com.senacor.testing.PollRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.inject.Inject;

@SpringJUnitConfig(Application.class)
class OneBaseClassToRuleThemAllTest {

    @Inject
    protected PollRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}