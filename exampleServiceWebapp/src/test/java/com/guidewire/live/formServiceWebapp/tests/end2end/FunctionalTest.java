package com.guidewire.live.formServiceWebapp.tests.end2end;

import com.guidewire.live.corelib.client.ClientConfig;
import com.guidewire.live.corelib.runtime.RuntimePropertyResolver;
import com.guidewire.live.exampleService.generated.api.ExampleServiceApi;
import com.guidewire.live.exampleService.generated.api.ExampleServiceApiFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;

@Test(groups = {"functional"})
public class FunctionalTest extends Assert {
  private ExampleServiceApi client;

  @BeforeClass
  public void beforeTest() throws Exception {
    Properties myStackProperties = new Properties();
    myStackProperties.load(this.getClass().getClassLoader().getResourceAsStream("myStack.properties"));
    System.getProperties().putAll(myStackProperties);

    Properties properties = new RuntimePropertyResolver("classpath:test-client.properties").getProperties();
    client = ExampleServiceApiFactory.newApi(new ClientConfig()
            .withProperties(properties));
  }

  @Test
  public void testJetFormImmediate() throws Exception {
    FunctionalTestFixture.testOperation(client);
  }

}
