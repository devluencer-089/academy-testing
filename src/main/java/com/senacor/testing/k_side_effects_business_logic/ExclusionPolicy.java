package com.senacor.testing.k_side_effects_business_logic;

import com.senacor.testing.Poll;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ExclusionPolicy {

    List<Poll> apply(List<Poll> polls);

    static ExclusionPolicy noExclusions() {
        return polls -> polls;
    }

    static ExclusionPolicy excludeOpen() {
        return polls -> polls.stream()
                .filter(Poll::isClosed)
                .collect(toList());
    }

    static ExclusionPolicy excludeUnparticipated() {
        return polls -> polls.stream()
                .filter(poll -> poll.getParticipantCount() > 0)
                .collect(toList());
    }

    static ExclusionPolicy excludeUnfinished() {
        return excludeOpen().and(excludeUnparticipated());
    }

    default ExclusionPolicy and(ExclusionPolicy exclusionPolicy) {
        return polls -> exclusionPolicy.apply(this.apply(polls));
    }
}
