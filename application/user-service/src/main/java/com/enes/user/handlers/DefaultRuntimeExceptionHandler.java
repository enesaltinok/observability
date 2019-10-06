package com.enes.user.handlers;

import com.enes.user.model.ApiErrorResponse;
import io.opentracing.Tracer;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultRuntimeExceptionHandler extends BaseExceptionHandler {

    public DefaultRuntimeExceptionHandler(Tracer tracer) {
        super(tracer, LoggerFactory.getLogger(DefaultRuntimeExceptionHandler.class));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> response(Throwable ex) {

        String category = "INTERNAL_SERVER_ERROR";
        String type = "UNEXPECTED";
        String code = "500001";

        logException(category, type, code, ex);

        ApiErrorResponse response = new ApiErrorResponse();
        response.setCategory(category);
        response.setType(type);
        response.setCode(code);
        response.setMessage("Something unexpected happened");
        response.setUserMessage("Unexpected error. Please report to support@enes.com with trace id");
        response.setTraceId(getTraceId());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
