package com.aj.camunda.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class CamundaServices {
    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    public List<ProcessInstance> getRunningProcessInstanceList() {
        try {
            List<ProcessInstance> runningProcessList = runtimeService.createProcessInstanceQuery().active().list();
            log.info("Running process list count is {}", runningProcessList.size());
            return runningProcessList;
        } catch (Exception e) {
            log.error("Failed to get running process instance list.", e);
            return null;
        }
    }

    public ProcessInstanceWithVariables startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        try {
            ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey(processDefinitionKey)
                    .businessKey(UUID.randomUUID().toString())
                    .setVariables(variables)
                    .executeWithVariablesInReturn();
            log.info("New process instance initiated. Process instance id is {}", processInstanceWithVariables.getId());
            return processInstanceWithVariables;
        } catch (Exception e) {
            log.error("Failed to start process instance.", e);
            return null;
        }
    }
}
