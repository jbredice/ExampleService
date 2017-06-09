package com.guidewire.live.formServiceImpl.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogUtil {
  public static final String MESSAGE = "message";
  public static final String RESULT = "result";
  public static final String TIME_MSEC = "timeMsec";
  public static final String ACTION = "action";
  public static final String SERVICE = "service";
  public static final String ENTRY_POINT = "entryPoint";
  public static final String REQUEST = "request";
  public static final String SERVICE_NAME = "FormService";

  public static void info(String action, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    info(logger, values);
  }

  public static void info(String action, String k1, Object v1, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    info(logger, values);
  }

  public static void info(String action, String k1, Object v1, String k2, Object v2, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    info(logger, values);
  }

  public static void info(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    info(logger, values);
  }

  public static void info(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    values.put(k4, v4);
    info(logger, values);
  }

  public static void info(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    values.put(k4, v4);
    values.put(k5, v5);
    info(logger, values);
  }

  public static void error(String action, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    error(logger, values, t);
  }

  public static void error(String action, String k1, Object v1, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    error(logger, values, t);
  }

  public static void error(String action, String k1, Object v1, String k2, Object v2, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    error(logger, values, t);
  }

  public static void error(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    error(logger, values, t);
  }

  public static void error(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    values.put(k4, v4);
    error(logger, values, t);
  }

  public static void error(String action, String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, Throwable t, Logger logger) {
    LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    values.put(ACTION, action);
    values.put(k1, v1);
    values.put(k2, v2);
    values.put(k3, v3);
    values.put(k4, v4);
    values.put(k5, v5);
    error(logger, values, t);
  }

  private static void error(Logger logger, LinkedHashMap<String, Object> values, Throwable t) {
    try {
      logger.error(serialize(addValues(values)), t);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private static void info(Logger logger, LinkedHashMap<String, Object> values) {
    try {
      logger.info(serialize(addValues(values)));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private static LinkedHashMap<String, Object> addValues(Map<String, Object> values) {
    LinkedHashMap<String, Object> newValues = new LinkedHashMap<>();
    newValues.put(SERVICE, SERVICE_NAME);
    newValues.put(ENTRY_POINT, getEntryPoint());
    newValues.putAll(values);
    newValues.put(REQUEST, getRequest());
    return newValues;
  }

  private static String getEntryPoint() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      if (request != null) {
        String requestURI = request.getRequestURI();
        if (requestURI != null) {
          String[] split = requestURI.split("/");
          if (split.length != 0) {
            return split[split.length - 1];
          }
        }
      }
    }
    return null;
  }

  public static Object getRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      if (request != null) {
        return request.getAttribute(REQUEST);
      }
    }
    return null;
  }

  public static void error(LoggedException exception, Logger logger) {
    LinkedHashMap<String, Object> newValues = new LinkedHashMap<>();
    newValues.put(SERVICE, SERVICE_NAME);
    newValues.put(ENTRY_POINT, getEntryPoint());
    newValues.put(ACTION, exception.getAction());
    Map<String, Object> values = exception.getExtraValues();
    if (values != null) {
      newValues.putAll(values);
    }
    newValues.put(REQUEST, getRequest());
    try {
      logger.error(serialize(newValues), exception);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private static String serialize(LinkedHashMap<String, Object> values) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(values);
  }
}
