package com.aj.camunda.tasks.customer;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("PlaceOrder")
public class PlaceOrder implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(PlaceOrder.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Customer - PlaceOrder Invoked");

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

        execution.setVariable("PlaceOrder", "Successful");

        execution.setVariable("customerName", "John Doe");
        execution.setVariable("phoneNumber", "1234567890");
        execution.setVariable("electronicItem", "TV");

        runtimeService.startProcessInstanceByMessage("startEstoreOrderProcess", execution.getProcessBusinessKey(), execution.getVariables());

        execution.setVariable("SendOrderToEStore", "Successful");
    }

}