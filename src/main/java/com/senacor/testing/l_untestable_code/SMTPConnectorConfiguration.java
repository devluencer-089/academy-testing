package com.senacor.testing.l_untestable_code;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Pavle Bektasevic on 20.07.2018.
 */
@Configuration
@RequiredArgsConstructor
public class SMTPConnectorConfiguration {

    private final Credentials credentials;

    @Bean
    public SMTPConnector smtpConnector() {
        return new SMTPConnector(
                credentials.getUsername(),
                credentials.getPassword()
        );
    }

}
