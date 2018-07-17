package com.senacor.testing;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class PollRepository {

    public static final String COLLECTION_NAME = "documents";
    public static final String ID_FIELD = "_id";

    private final MongoOperations mongoOperations;

    @Inject
    public PollRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }



    public List<Poll> findAllOfUser(String userId) {
        Query query = new Query().addCriteria(new Criteria().orOperator(
                where("ownerId").is(userId),
                where("participants.userId").in(userId)
        ));
        return mongoOperations.find(query, Poll.class, COLLECTION_NAME);
    }

    public Optional<Poll> findPoll(String pollId) {
        Query query = new Query().addCriteria(where(ID_FIELD).is(pollId));
        return Optional.ofNullable(mongoOperations.findOne(query, Poll.class));
    }

    public void deleteAll() {
        mongoOperations.remove(new Query(), COLLECTION_NAME);
    }

    public void insert(List<Poll> polls) {
        mongoOperations.insert(polls, COLLECTION_NAME);
    }

    public void insert(Poll poll) {
        mongoOperations.insert(poll, COLLECTION_NAME);
    }
}
