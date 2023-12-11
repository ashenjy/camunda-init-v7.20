package com.aj.camunda.tasks.estore;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("PrepareOrder")
public class PrepareOrder implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(PrepareOrder.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("E-Store PrepareOrder Invoked");

        execution.setVariable("isOrderPrepared", true);
    }

}