package com.senacor.testing.c_specs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class B_LoginServiceTest {


    //The "multiple-tests-per-function-pattern"

    //A common anti pattern is to have field and functions as grouping brackets around tests related to that function or field.
    //Tests are focused on functions, not behavior.
    //It is difficult to read, difficult to understand what they test and difficult and to derive any behavior from this sort of test.
    //While this technique helps to scope fixtures into nested blocks, it does not have any benefits other than providing test coverage.

    LoginService sut;

    @Nested
    class Login {

        @Test
        public void testLoginSuccess() {

        }

        @Test
        public void testLoginWithInvalidPassword() {

        }


        @Test
        public void testLoginFailure() {

        }

        @AfterEach
        void logout() {
            //log-out user
        }
    }

    @Nested
    class Logout {

        @BeforeEach
        void setUp() {
            //log-in user
        }

        @Test
        public void testLoginSuccess() {

        }

        @Test
        public void testLoginMultipleTimes() {

        }
    }

}