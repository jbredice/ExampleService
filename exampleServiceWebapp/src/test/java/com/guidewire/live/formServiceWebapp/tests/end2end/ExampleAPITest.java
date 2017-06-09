package com.guidewire.live.formServiceWebapp.tests.end2end;

import com.guidewire.live.exampleService.generated.ApiException;
import com.guidewire.live.exampleService.generated.model.Request;
import org.testng.annotations.Test;

@Test
public class ExampleAPITest extends BaseAPITest {

//  @Test
//  public void testFormTypeNotSpecified() {
//    Request request = new Request();
//    assertFailure(() -> client.example(request), SC_BAD_REQUEST, "Please provide the form type.");
//  }
//
//  @Test
//  public void testStatus() throws Exception {
//    String status = client.getStatus();
//    assertTrue(status.startsWith("Example Service version:"));
//  }
//
//  @Test
//  public void testHome() throws Exception {
//    assertTrue(get("/").startsWith("Example Service version:"));
//  }
//
//  @Test
//  public void test500() throws Exception {
//    assertFailure(() -> get("/error/500"), 500, "Unexpected status");
//  }
//
//  @Test
//  public void test403() throws Exception {
//    assertFailure(() -> get("/error/403"), 403, "<html><body>Forbidden<br/></body></html>");
//  }

  public static void assertFailure(RunnableWithException r, int code, String... errors) {
    try {
      r.run();
      fail("expected exception");
    } catch (ApiException e) {
      String message = e.getResponseBody();
      assertEquals(e.getCode(), code, message);
      if (errors != null) {
        for (String error : errors) {
          assertTrue(message.contains(error), "Expected error: " + error + "\nbut found: " + message);
        }
      }
    } catch (Exception e) {
      fail("", e);
    }
  }

  interface RunnableWithException {
    void run() throws Exception;
  }
}
