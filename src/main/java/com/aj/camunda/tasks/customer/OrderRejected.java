package com.aj.camunda.tasks.customer;

import camundajar.impl.com.google.gson.Gson;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component("OrderRejected")
public class OrderRejected implements JavaDelegate {

    @Autowired
    private Gson gsonParser;
    private final Logger LOGGER = Logger.getLogger(OrderRejected.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Customer - OrderRejected Invoked");
        execution.setVariable("OrderRejected", "Invoked");

        LOGGER.info("Final Execution Variables :" + gsonParser.toJson(execution.getVariables(), Map.class));


    }
}