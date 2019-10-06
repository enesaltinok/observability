package com.enes.greeting.handlers;

import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

public class BaseExceptionHandler {

    private static final String ERROR_CATEGORY = "error_category";

    private static final String ERROR_TYPE = "error_type";

    private static final String ERROR_CODE = "error_code";

    private static final String TRACE_ID = "trace_id";

    private final Logger logger;

    private final Tracer tracer;

    public BaseExceptionHandler(Tracer tracer, Logger logger) {
        this.tracer = tracer;
        this.logger = logger;
    }

    String getTraceId() {
        return tracer.activeSpan().context().toTraceId();
    }

    void logException(String category, String type, String code, Throwable ex) {

        String traceId = getTraceId();
        addErrorParametersToMDC(category, type, code, traceId);
        logger.error(ex.getMessage(), ex);
        removeErrorParametersFromMDC();
    }

    private void addErrorParametersToMDC(String category, String type, String code, String traceId) {
        MDC.put(ERROR_CATEGORY, category);
        MDC.put(ERROR_TYPE, type);
        MDC.put(ERROR_CODE, code);
        MDC.put(TRACE_ID, traceId);
    }

    private void removeErrorParametersFromMDC() {
        MDC.remove(ERROR_CATEGORY);
        MDC.remove(ERROR_TYPE);
        MDC.remove(ERROR_CODE);
        MDC.remove(TRACE_ID);
    }

    Map<String, Object> getBindingFieldParameters(BindingResult bindingResult) {
        Map<String, Object> parameters = new HashMap<>();

        for (ObjectError objectError : bindingResult.getAllErrors()) {

            if (objectError instanceof FieldError) {

                FieldError fieldError = (FieldError) objectError;
                parameters.put(fieldError.getField(), fieldError.getRejectedValue());
            }
            else {
                parameters.put(objectError.getObjectName(), null);
            }
        }

        return parameters;
    }
}
