package com.guidewire.live.formServiceWebapp.controllers;

import com.guidewire.live.formServiceWebapp.ServiceWebappConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PublicController {
  @Value("${application_version}")
  private String appVersion;

  @RequestMapping(value = ServiceWebappConstants.HOME_PATH)
  public String indexMenu() {
    return "redirect:" + ServiceWebappConstants.STATUS_PATH;
  }

  @RequestMapping(value = ServiceWebappConstants.STATUS_PATH, method = RequestMethod.GET)
  @ResponseBody
  public String status() {
    return "Example Service version: " + appVersion;
  }

  @RequestMapping(value = ServiceWebappConstants.DOCS_PATH,  method = RequestMethod.GET)
  public String getDocs() {
    return "redirect:" + ServiceWebappConstants.DOCS_PATH + "/index.html";
  }

  @RequestMapping(value = ServiceWebappConstants.ERROR500_PATH)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public String error500(HttpServletResponse response) {
    return String.format("Unexpected status[%d]. Code version: %s", response.getStatus(), appVersion);
  }

  @RequestMapping(value = ServiceWebappConstants.ERROR403_PATH)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public String error403() {
    return "<html><body>Forbidden<br/></body></html>";
  }
}