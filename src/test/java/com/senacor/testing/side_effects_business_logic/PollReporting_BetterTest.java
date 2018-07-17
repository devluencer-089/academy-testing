package com.senacor.testing.side_effects_business_logic;


import com.senacor.testing.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PollReporting_BetterTest {

    PollReporting_Better sut;

    @Mock
    MyFileSystem fileSystem;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PollReporting_Better(fileSystem);
    }

    @Nested
    class FinishedPollsReporting {

        @Test
        void itAppliesExclusionPolicyBeforeWriting() {
            List<Poll> polls = new ArrayList<>();
            List<Poll> filteredPolls = new ArrayList<>();
            
            ExclusionPolicy policy = mock(ExclusionPolicy.class);
            when(policy.apply(polls)).thenReturn(filteredPolls);

            sut.reportFinishedPolls(polls, policy);

            verify(policy).apply(polls);
            verify(fileSystem).write(filteredPolls);

        }

    }

}