package com.guidewire.live.formServiceImpl.logging;

import java.util.Map;

public class LoggedException extends RuntimeException {
  private String action;
  private int httpStatus;
  private Map<String, Object> extraValues;

  public LoggedException(String action, String message, int httpStatus, Throwable cause, Map<String, Object> extraValues) {
    super(message, cause);
    this.action = action;
    this.httpStatus = httpStatus;
    this.extraValues = extraValues;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getAction() {
    return action;
  }

  public Map<String, Object> getExtraValues() {
    return extraValues;
  }
}
