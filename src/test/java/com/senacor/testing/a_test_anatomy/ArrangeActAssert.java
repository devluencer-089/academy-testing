package com.senacor.testing.a_test_anatomy;

import com.senacor.testing.Email;
import com.senacor.testing.Poll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.internal.util.collections.Sets.newSet;

public class ArrangeActAssert {


    @Test
    public void itConsistsOfThreeAs() {
        //arrange
        Poll sut = Poll.newBuilder()
                .participant("id1", Email.of("1@1.com"))
                .participant("id2", Email.of("2@2.com"))
                .build();

        //act
        int count = sut.getParticipantCount();

        //assert
        assertEquals(2, count);
    }


    @Test
    public void itMustRespectTheSingleAssertionRule() {
        //arrange
        Poll sut = Poll.newBuilder()
                .participant("id1", Email.of("1@1.com"))
                .participant("id2", Email.of("2@2.com"))
                .build();

        //1st assertion
        assertEquals(2, sut.getParticipantCount());

        //2nd completely unrelated assertion
        assertEquals(2, sut.getParticipantEmails().size());
    }

    @Test
    public void itCanContainsMultiplePhysicalAssertionsBelongingToTheSameLogicalAssertion() {
        Poll sut = Poll.newBuilder()
                .participant("id1", Email.of("shared-email.com"))
                .participant("id2", Email.of("shared-email.com"))
                .build();

        Set<Email> emails = sut.getParticipantEmails();
        
        //1st physical assertion
        assertEquals(1, emails.size());
        //2nd physical assertion
        assertEquals(newSet(Email.of("shared-email.com")), emails);
    }

    @Test
    public void noAssertionTest() {
        Service sut = new Service();

        sut.doSomething();

        //Let's hope no exception is thrown and we are good for deployment.
        //Also, test coverage is increased without testing something. Awesome!
    }

    @Test
    public void test() {
        //it a test, because the name says so.
    }


    private class Service {
        public void doSomething() {

        }
    }
}
