package com.guidewire.live.exampleServiceWebapp.tests.end2end;

import com.guidewire.live.exampleService.generated.ApiClient;
import com.guidewire.live.exampleService.generated.ApiException;
import com.guidewire.live.exampleService.generated.api.ExampleServiceApi;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseAPITest extends Assert {
  private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPITest.class);

  private Server server;
  protected String endpoint;
  protected ExampleServiceApi client;

  @BeforeClass
  public void beforeTest() throws Exception {
//    Properties myStackProperties = new Properties();
//    myStackProperties.load(this.getClass().getClassLoader().getResourceAsStream("myStack.properties"));
//    System.getProperties().putAll(myStackProperties);
//
//    ServerSocket socket = new ServerSocket(0);
//    int port = socket.getLocalPort();
//    socket.close();
//    server = WebApp.startServer(port);
//    endpoint = String.format("http://localhost:%d", port);
//    LOGGER.info(String.format("serviceEndpoint[%s]", endpoint));
//    // we run this again for the *client* context here to ensure ordering of init...
//    System.setProperty("service.http.endpoint", endpoint);
//
//    WebAppContext ctx = (WebAppContext) server.getHandler();
//    WebApplicationContext app = getApp(ctx);
//    ExampleServiceImpl exampleService = app.getBean(ExampleServiceImpl.class);
//    exampleService.setEnvName("test-" + UUID.randomUUID().toString());
//
//    Properties properties = new RuntimePropertyResolver("classpath:test-client.properties").getProperties();
//    ClientConfig config = new ClientConfig()
//            .withProperties(properties)
//            .withEndpointUrl(endpoint);
//    client = ExampleServiceApiFactory.newApi(config);
  }

  private static WebApplicationContext getApp(WebAppContext context) {
    ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(context.getClassLoader());
      return ContextLoader.getCurrentWebApplicationContext();
    } finally {
      Thread.currentThread().setContextClassLoader(oldClassLoader);
    }
  }

  @AfterClass
  public void afterTest() throws Exception {
//    server.stop();
  }

  protected String get(String path) throws ApiException {
    ApiClient apiClient = client.getApiClient();
    final String[] localVarAccepts = {"application/json"};
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    final String localVarContentType = apiClient.selectHeaderContentType(new String[]{
    });
    return apiClient.invokeAPI(path, "GET", new ArrayList<>(), null, new HashMap<>(), new HashMap<>(),
            localVarAccept, localVarContentType, new String[]{}, new GenericType<String>() {
            });
  }
}
HELLO
