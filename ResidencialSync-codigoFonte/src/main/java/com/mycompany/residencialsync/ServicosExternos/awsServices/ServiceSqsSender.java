package com.mycompany.residencialsync.ServicosExternos.awsServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.residencialsync.ServicosExternos.awsServices.dto.SqsMessageRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class ServiceSqsSender {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SqsClient sqsClient;
    private Logger logger = LoggerFactory.getLogger(ServiceSqsSender.class);

    @Value("${aws.services.ses.emailSender}")
    private String sender;

    @Value("${aws.services.sqs.queue-url}")
    private String queueUrl;

    public ServiceSqsSender(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String to, String subject, String body) throws JsonProcessingException {

        SqsMessageRequestDto messageRequestDto = new SqsMessageRequestDto(
                to,
                subject,
                body,
                sender

        );

        String jsonString = objectMapper.writeValueAsString(messageRequestDto);

        SendMessageRequest sendMessageStandardQueue = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(jsonString)
                .delaySeconds(5)
                .build();

        sqsClient.sendMessage(sendMessageStandardQueue);
        logger.info("Sending message");
    }

}
