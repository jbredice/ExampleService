package com.guidewire.live.formServiceWebapp.tests.standalone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class WebApp {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebApp.class);

  public static final int HTTP_PORT = 8137;

  public static void main(String[] args) throws Exception {
    startServer(HTTP_PORT);
  }

  public static Server startServer(int port) throws Exception {
    Properties properties = new Properties();
    InputStream stream = WebApp.class.getClassLoader().getResourceAsStream("myStack.properties");
    if (stream == null) {
      System.err.println("Cannot find myStack.properties");
    }
    properties.load(stream);
    System.getProperties().putAll(properties);

    // TODO this should be inherited
    if (System.getProperty("PARAM1") == null) {
      System.setProperty("PARAM1", "classpath:runtimeConfigTest.properties");
    }

    Server server = new Server(port);
    WebAppContext context = new WebAppContext();
    //context.setVirtualHosts(new String[]{InetAddress.getLocalHost().getCanonicalHostName()});

    File wd = new File(".");
    File formServiceWebapp = new File(wd, "formServiceWebapp");
    if (formServiceWebapp.exists()) {
      wd = formServiceWebapp;
    }
    File resourceBase = new File(wd, "src/main/webapp");
    File webXml = new File(resourceBase, "WEB-INF/web.xml");
    if (!webXml.exists()) {
      throw new RuntimeException(String.format("Cannot find web.xml at '%s'", webXml.getCanonicalPath()));
    }

    LOGGER.info("ResourceBase[{}]", resourceBase);
    context.setDescriptor(webXml.getCanonicalPath());
    context.setResourceBase(resourceBase.getCanonicalPath());
    context.setDefaultsDescriptor(new ClassPathResource("webdefault.xml").getURI().toString());

    context.setContextPath("/");
    context.setParentLoaderPriority(true);
    server.setHandler(context);

    server.start();

    return server;
  }
}
