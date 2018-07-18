package com.senacor.testing.g_test_doubles;

import com.senacor.testing.Poll;
import com.senacor.testing.PollRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PollRepositoryStub extends PollRepository {

    protected final List<Poll> polls = new ArrayList<>();

    public PollRepositoryStub() {
        super(null);
    }

    void addPoll(Poll poll) {
        polls.add(poll);
    }

    @Override
    public Optional<Poll> findPoll(String pollId) {
        return polls.stream().findFirst();
    }

    @Override
    public void insert(Poll poll) {
        addPoll(poll);
    }

    //UNSUPPORTED OPERATIONS

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

}
