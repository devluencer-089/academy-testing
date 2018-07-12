package com.senacor.testing;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(Application.class)
public class EmbeddedMongoTest {


    @Test
    public void that() {
        MongoClient mongoClient = new MongoClient();
        MongoIterable<String> strings = mongoClient.listDatabaseNames();
    }
}
