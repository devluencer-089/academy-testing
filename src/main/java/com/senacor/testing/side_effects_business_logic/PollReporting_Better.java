package com.senacor.testing.side_effects_business_logic;

import com.senacor.testing.Poll;

import java.util.List;

public class PollReporting_Better {

    private final MyFileSystem fileSystem;

    public PollReporting_Better(MyFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    void reportFinishedPolls(List<Poll> polls, ExclusionPolicy policy) {
        fileSystem.write(policy.apply(polls));
    }
}
