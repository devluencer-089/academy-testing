package com.senacor.testing.c_specs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class C_LoginServiceSpec {

    LoginService sut;

    //a specification of the objects behavior under certain contexts and preconditions

    /**
     * Describe the context of this scope or preconditions that must be satisfied
     * What is the starting point when describing the SUT's behavior.
     */
    @Nested
    class WhenTheUserIsNotLoggedIn {


        @Test
        void heMustEnterValidCredentialsToObtainASessionToken() {
        }

        //don't hesitate to use long specifications if necessary
        @Test
        public void heHasAtMostThreeAttemptsToEnterTheCorrectCredentialsBeforeHisAccountIsLocked() {

        }

        @Test
        public void loggingOutHasNoEffect() {

        }


        @AfterEach
        void logout() {
            //log-out user
        }
    }

    /**
     * "Contexts" should not have overlapping preconditions.
     * They are usually binary (whenLoggedIn/whenLoggedOut) but don't have to be.
     */
    @Nested
    class WhenTheUserIsLoggedIn {

        @BeforeEach
        void login() {
            //log-in user
            //this is a usually a transient fresh fixture
        }

        @Test
        public void heCannotLoginMultipleTimes() {

        }

        @Test
        public void loggingOutRevokesHisSessionToken() {

        }
    }

    /**
     * Move validation and corner cases always to the bottom of your specification.
     * The must common use cases and the default behavior should be on top, so readers can get a quick glipse at
     * the important stuff
     */

    @Nested
    class ItAlwaysRejects {

        @Test
        void nullAsInputValue() {

        }

        @Test
        void malformedEmailAddresses() {

        }
    }

}