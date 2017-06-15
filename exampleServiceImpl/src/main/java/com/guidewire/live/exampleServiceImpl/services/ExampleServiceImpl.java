package com.guidewire.live.exampleServiceImpl.services;

import com.guidewire.live.exampleService.generated.model.Request;
import com.guidewire.live.exampleService.generated.model.Response;
import com.guidewire.live.exampleServiceImpl.ExampleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Forms Service -- handle marshalling of forms, native code execution, etc
 */
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {

    @Value("${com.guidewire.live.exampleService.envName}")
    private String envName;

    @Override
    public Response operation(Request request, String companyDn) {
        return new Response().value("success");
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }
}
HELLO
