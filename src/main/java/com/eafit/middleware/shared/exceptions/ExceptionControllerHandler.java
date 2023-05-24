package com.eafit.middleware.shared.exceptions;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eafit.middleware.shared.exceptions.CustomException.Response;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<Response> runtimeException(CustomException e) {
    return new ResponseEntity<>(e.getResponse(), e.statusCode);
  }
}
