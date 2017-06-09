package com.guidewire.live.formServiceTest;

import com.guidewire.live.corelib.client.ClientConfig;
import com.guidewire.live.corelib.client.retry.NoRetryPolicy;
import com.guidewire.live.exampleService.generated.api.ExampleServiceApi;
import com.guidewire.live.exampleService.generated.api.ExampleServiceApiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class StandaloneTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneTest.class);

    public static void main(String args[]) throws Exception {
        Properties props = new Properties();
        props.load(StandaloneTest.class.getClassLoader().getResourceAsStream("standalone/client.properties"));
        ExampleServiceApi exampleServiceApi = ExampleServiceApiFactory.newApi(new ClientConfig()
                .withProperties(props)
                .withRetryPolicy(NoRetryPolicy.INSTANCE));
        System.out.println(exampleServiceApi.getStatus());
    }
}
