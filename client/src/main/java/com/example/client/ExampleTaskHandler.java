package com.example.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.grpc.client.domain.ExternalTask;
import org.camunda.bpm.engine.grpc.client.service.ExternalTaskService;
import org.camunda.bpm.engine.grpc.client.subscription.impl.AbstractSubscriptionHandler;
import org.camunda.bpm.engine.grpc.client.subscription.impl.SubscriptionHandlerParameters;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
public class ExampleTaskHandler extends AbstractSubscriptionHandler<ExampleTaskHandler.Parameters> {

    @Data
    static class Parameters {
        String exampleVariable; // Context variable
    }

    public ExampleTaskHandler(ObjectMapper objectMapper, SubscriptionHandlerParameters subscriptionHandlerParameters, ExternalTaskService externalTaskService) {
        super(objectMapper, subscriptionHandlerParameters, externalTaskService);
    }

    @Override
    public String getTopicName() {
        return "ExampleExternalTask";
    }

    @Override
    protected void validate(Parameters parameters, Errors errors) {
        // here you can validate context variables

        if (parameters.exampleVariable == null) {
            errors.rejectValue("exampleVariable", "Value can not be empty!");
        }
    }

    @Override
    protected Map<String, Object> handle(Parameters parameters, ExternalTask externalTask) throws Throwable {
        log.info("External task handled with context variables {}", parameters);

        System.out.println("================================");
        System.out.println("Topic: ExampleExternalTask");
        System.out.println("--------------------------------");
        System.out.println(parameters.getExampleVariable());
        System.out.println("================================");

        // return variables collection to be merged into context
        return Collections.emptyMap();
    }
}
