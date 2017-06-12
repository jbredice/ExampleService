package com.guidewire.live.formServiceWebapp.controllers;

import com.guidewire.live.exampleService.generated.model.Request;
import com.guidewire.live.exampleService.generated.model.Response;
import com.guidewire.live.formServiceImpl.ExampleService;
import com.guidewire.live.formServiceImpl.logging.LoggedException;
import com.guidewire.live.formServiceWebapp.ServiceWebappConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.guidewire.live.formServiceImpl.logging.LogUtil.error;

@RestController
@Secured("ROLE_FORMSERVICE-USER")
@RequestMapping(ServiceWebappConstants.APP_BASE)
public class ExampleController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

  @Autowired
  private ExampleService exampleService;

  @RequestMapping(value = ServiceWebappConstants.APP_OPERATION_SEGMENT, method = RequestMethod.POST)
  public Response merge(@RequestBody Request request, HttpServletRequest req, Authentication authentication) {
    instrument(req, request);
    String companyId = "gw";
    return exampleService.operation(request, companyId);
  }

  // exception handling

  @ExceptionHandler(Exception.class)
  ResponseEntity<String[]> handleException(Throwable exception, HttpServletRequest request) {
    LoggedException logException = exception instanceof LoggedException ?
            (LoggedException) exception : new LoggedException(null, exception.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR, exception, null);
    error(logException, LOGGER);
    return new ResponseEntity<>(new String[]{logException.getMessage()}, org.springframework.http.HttpStatus.valueOf(logException.getHttpStatus()));
  }

  private void instrument(HttpServletRequest req, Request request) {
    req.setAttribute("request", createLoggableRequest(request));
  }

  private Request createLoggableRequest(Request request) {
    Request loggableRequest = new Request();
    loggableRequest.setValue(request.getValue());
    return loggableRequest;
  }

}
