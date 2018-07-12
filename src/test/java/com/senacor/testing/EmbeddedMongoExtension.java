package com.senacor.testing;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;

public class EmbeddedMongoExtension implements BeforeAllCallback, AfterAllCallback {

    private MongodStarter starter = MongodStarter.getDefaultInstance();
    private MongodProcess mongod;
    private MongodExecutable mongodExecutable = null;

    public EmbeddedMongoExtension() {
    }

    private void createEmbeddedMongoInstance() {
        IMongodConfig mongodConfig = null;
        try {
            mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.V3_6)
                    .net(new Net(27017, Network.localhostIsIPv6()))
                    .build();
            mongodExecutable = starter.prepare(mongodConfig);
            mongod = mongodExecutable.start();

        } catch (IOException e) {
            if (this.mongodExecutable != null) {
                this.mongodExecutable.stop();
            }
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
        if (mongod != null)
            mongod.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        createEmbeddedMongoInstance();
    }
}