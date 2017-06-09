package com.guidewire.live.formServiceImpl.tests;

import com.guidewire.live.exampleService.generated.model.Request;
import com.guidewire.live.formServiceImpl.logging.LoggedException;
import com.guidewire.live.formServiceImpl.services.ExampleServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.UUID;

public class ExampleServiceImplTest extends Assert {
  private ExampleServiceImpl formService;

  @BeforeClass
  public void setup() throws Exception {
//    // force a system property override for certain props (especially MyStack...)
//    Properties properties = new Properties();
//    properties.load(this.getClass().getClassLoader().getResourceAsStream("myStack.properties"));
//    System.getProperties().putAll(properties);
//
//    String envName = "test-" + UUID.randomUUID().toString();
//
//    final ApplicationContext context = new ClassPathXmlApplicationContext("test-formService.xml");
//
//    formService = (ExampleServiceImpl) context.getBean("formService");
//    formService.setEnvName(envName);
  }

  @Test
  public void testValidation() {
//    Request request = new Request();
//    assertException(request, "gw", SC_BAD_REQUEST, "something.");
  }


  private void assertException(Request request, String companyDn, int status, String message) {
    assertFailure(() -> formService.operation(request, companyDn), status, message);
  }

  public static void assertFailure(RunnableWithException r, int code, String... errors) {
    try {
      r.run();
      fail("expected exception");
    } catch (LoggedException e) {
      String message = e.getMessage();
      assertEquals(e.getHttpStatus(), code, message);
      if (errors != null) {
        for (String error : errors) {
          assertTrue(message.contains(error), "Expected error: " + error + "\nbut found: " + message);
        }
      }
    } catch (Exception e) {
      fail("", e);
    }
  }

}
