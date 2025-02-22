package com.mycompany.residencialsync.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsConfig {

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build();
    }


}
