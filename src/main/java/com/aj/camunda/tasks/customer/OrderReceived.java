package com.aj.camunda.tasks.customer;

import camundajar.impl.com.google.gson.Gson;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component("OrderReceived")
public class OrderReceived implements JavaDelegate {

    @Autowired
    private Gson gsonParser;
    private final Logger LOGGER = Logger.getLogger(OrderReceived.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Customer - OrderReceived Invoked");
        execution.setVariable("Customer - OrderReceived", "Invoked");

        LOGGER.info("Final Execution Variables :" + gsonParser.toJson(execution.getVariables(), Map.class));


    }
}