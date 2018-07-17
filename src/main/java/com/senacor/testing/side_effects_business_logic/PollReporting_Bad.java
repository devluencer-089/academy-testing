package com.senacor.testing.side_effects_business_logic;

import com.senacor.testing.Poll;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PollReporting_Bad {

    private final MyFileSystem fileSystem;

    public PollReporting_Bad(MyFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    void reportFinishedPolls(List<Poll> polls) {
        List<Poll> closedPolls = polls.stream()
                .filter(poll -> poll.getParticipantCount() > 0)
                .filter(Poll::isClosed)
                .collect(toList());

        fileSystem.write(closedPolls);
    }
}
