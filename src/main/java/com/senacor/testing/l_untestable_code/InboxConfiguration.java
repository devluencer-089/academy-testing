package com.senacor.testing.l_untestable_code;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Pavle Bektasevic on 20.07.2018.
 */
@Configuration
@RequiredArgsConstructor
public class InboxConfiguration {

    private final Credentials credentials;

    @Bean
    public Inbox inbox() {
        return new Inbox(
                credentials.getUsername(),
                credentials.getPassword()
        );
    }
}
