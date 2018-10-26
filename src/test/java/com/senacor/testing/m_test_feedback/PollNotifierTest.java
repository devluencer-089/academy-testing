package com.senacor.testing.m_test_feedback;

import com.senacor.testing.Email;
import com.senacor.testing.Poll;
import com.senacor.testing.PollRepository;
import com.senacor.testing.o_async.Mailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class PollNotifierTest {

    PollNotifier sut;

    @Mock
    PollRepository repositoy;

    @Mock
    Mailer mailer;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PollNotifier(repositoy, mailer);
    }

    @Test
    void itSendsAMessageToEachParticipantsOfThePoll() {
        Email hans = Email.of("hans@gmail.com");
        Email paul = Email.of("paul@gmail.com");

        Poll poll = Poll.newBuilder()
                .participant("1", Email.of("hans@gmail.com"))
                .participant("1", Email.of("paul@gmail.com"))
                .build();

        String message = "hi 👋";

        when(repositoy.findPoll(anyString())).thenReturn(Optional.of(poll));

        sut.mailParticipants("1", message);

        ArgumentCaptor<Email> messageCaptor = ArgumentCaptor.forClass(Email.class);
        verify(mailer, times(2)).send(eq(message), messageCaptor.capture());
        assertThat(messageCaptor.getAllValues()).containsOnly(
                hans,
                paul);
    }
}