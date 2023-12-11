package com.aj.camunda.rest;

import camundajar.impl.com.google.gson.Gson;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceWithVariablesDto;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/{key}")
public class WorkflowInvoker {

    @Autowired
    private Gson gsonParser;

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping(value = "/start", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public String start(@PathVariable String key,
            @RequestBody String requestBody) throws Exception {
        try {

            Map<String, Object> variables = gsonParser.fromJson(requestBody, Map.class);

            String businessKey = variables.containsKey("businessKey") ? (String) variables.get("businessKey")
                    : UUID.randomUUID().toString();

            // start process
            ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey(key)
                    .businessKey(businessKey)
                    .setVariables(variables)
                    .executeWithVariablesInReturn();

            // handle execution variables
            VariableMap vm = processInstanceWithVariables.getVariables();
            Map<String, Object> values = new HashMap<>();
            for (String variableName : vm.keySet()) {
                VariableValueDto valueDto = VariableValueDto.fromTypedValue(vm.getValueTyped(variableName), true);
                values.put(variableName, valueDto.getValue());
            }
            return gsonParser.toJson(values, Map.class);

        } catch (Exception e) {
            // TODO: Response 401 message
            return e.getMessage();
        }
    }
}
