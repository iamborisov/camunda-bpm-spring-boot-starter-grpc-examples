package com.example.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.grpc.client.domain.Variables;
import org.camunda.bpm.engine.grpc.client.service.MessageCorrelationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleMessageSender {

    private final MessageCorrelationService messageCorrelationService;

    @PostConstruct
    public void send() {
        try {
            log.info("Sending 'ExampleMessageEvent' message");

            for (int i = 0; i < 10; i++) {
                messageCorrelationService.createMessage(
                    "ExampleMessageEvent",
                    Variables.from(Map.of(
                        "exampleVariable", "Hello world! #" + i
                    ))
                );
            }
        } catch (Throwable t) {
            log.error("Exception on sending message", t);
        }
    }
}
