package com.guidewire.live.formServiceImpl;


import com.guidewire.live.exampleService.generated.model.Request;
import com.guidewire.live.exampleService.generated.model.Response;

public interface ExampleService {

  Response operation(Request request, String companyDn);

}
