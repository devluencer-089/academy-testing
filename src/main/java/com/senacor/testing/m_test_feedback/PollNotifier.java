package com.senacor.testing.m_test_feedback;

import com.senacor.testing.Participant;
import com.senacor.testing.Poll;
import com.senacor.testing.PollRepository;
import com.senacor.testing.o_async.Mailer;

import java.util.Optional;

/**
 * This class has a design issue
 */
public class PollNotifier {

    private final PollRepository repository;
    private final Mailer mailer;

    public PollNotifier(PollRepository repository, Mailer mailer) {
        this.repository = repository;
        this.mailer = mailer;
    }

    public void mailParticipants(String pollId, String message) {
        Optional<Poll> poll = repository.findPoll(pollId);
        poll.map(Poll::getParticipants).ifPresent(participants -> {
            participants.stream().
                    map(Participant::getEmail)
                    .forEach(emailAddress -> mailer.send(message, emailAddress));
        });
    }
}
