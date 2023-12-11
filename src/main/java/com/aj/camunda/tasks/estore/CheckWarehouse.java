package com.aj.camunda.tasks.estore;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("CheckWarehouse")
public class CheckWarehouse implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(CheckWarehouse.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("E-Store CheckWarehouse Invoked");
        execution.setVariable("GoodsExistInWarehouse", true);

    }

}