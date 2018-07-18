package com.senacor.testing.g_test_doubles;

import com.google.common.collect.Lists;
import com.senacor.testing.Poll;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PollRepositoryMock extends PollRepositorySpy {
    public int findCount = 0;
    public int insertCount = 0;

    void assertPollsInsertedContainsExcactly(Poll poll, Poll... morePolls) {
        List<Poll> expected = Lists.asList(poll, morePolls);
        assertThat(super.polls).containsExactlyElementsOf(expected);
    }
}
