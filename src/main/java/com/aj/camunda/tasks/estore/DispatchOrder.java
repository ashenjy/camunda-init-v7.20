package com.aj.camunda.tasks.estore;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("DispatchOrder")
public class DispatchOrder implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(DispatchOrder.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("E-Store DispatchOrder Invoked");

        execution.setVariable("DispatchOrder", "Successful");
        execution.setVariable("orderReceived", true);

        execution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("EstoreOrderMessage")
                .processInstanceBusinessKey(execution.getProcessBusinessKey())
                .setVariables(execution.getVariables())
                .correlate();
    }

}