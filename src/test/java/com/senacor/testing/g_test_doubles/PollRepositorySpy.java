package com.senacor.testing.g_test_doubles;

import com.senacor.testing.Poll;

import java.util.Optional;

public class PollRepositorySpy extends PollRepositoryStub {
    public int findCount = 0;
    public int insertCount = 0;

    @Override
    public Optional<Poll> findPoll(String pollId) {
        findCount++;
        return super.findPoll(pollId);
    }

    @Override
    public void insert(Poll poll) {
        insertCount++;
        super.insert(poll);
    }
}
