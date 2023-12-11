package com.aj.camunda.tasks.estore;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("RejectOrder")
public class RejectOrder implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(RejectOrder.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("E-Store RejectOrder Task Invoked");
        execution.setVariable("rejectOrder", true);
        execution.setVariable("orderReceived", false);

    }

}