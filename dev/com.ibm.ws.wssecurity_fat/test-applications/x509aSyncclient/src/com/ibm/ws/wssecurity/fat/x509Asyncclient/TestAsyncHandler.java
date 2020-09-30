package com.ibm.ws.wssecurity.fat.x509Asyncclient;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import test.wssecfvt.x509async.types.ResponseString;

public class TestAsyncHandler implements AsyncHandler<ResponseString> {
  private ResponseString reply;

  public void handleResponse(Response<ResponseString> response) {
    try {
      System.out.println("In ResponseHandler");
      reply = response.get();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String getResponse() {
    System.out.println("In getResponse");
    return reply.getStringres();
  }
}