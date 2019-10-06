package com.enes.user.handlers;

import com.enes.user.exceptions.BusinessLogicException;
import com.enes.user.model.ApiErrorResponse;
import io.opentracing.Tracer;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessLogicExceptionHandler extends BaseExceptionHandler {

    public BusinessLogicExceptionHandler(Tracer tracer) {
        super(tracer, LoggerFactory.getLogger(BusinessLogicExceptionHandler.class));
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ApiErrorResponse> response(Throwable ex) {

        BusinessLogicException exception = (BusinessLogicException) ex;
        String category = exception.getCategory();
        String type = exception.getType();
        String code = exception.getCode();
        String userMessage = exception.getMessage();
        int httpStatus = exception.getHttpStatus();
        Map<String, Object> parameters = exception.getParameters();

        logException(category, type, code, ex);

        ApiErrorResponse response = new ApiErrorResponse();
        response.setCategory(category);
        response.setType(type);
        response.setCode(code);
        response.setMessage(exception.getMessage());
        response.setUserMessage(userMessage);
        response.setParameters(parameters);
        response.setTraceId(getTraceId());

        return new ResponseEntity<>(response, HttpStatus.valueOf(httpStatus));
    }
}
