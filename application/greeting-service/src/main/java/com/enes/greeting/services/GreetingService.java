package com.enes.greeting.services;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.log.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    private static final String GREETING_MESSAGE = "Hello %s!";

    private final Tracer tracer;

    public GreetingService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void greet(String name) {

        Span span = tracer.buildSpan("greet").start();
        try(Scope scope = tracer.scopeManager().activate(span)) {
            String message = prepareMessage(name);
            span.setTag("message", message);
            sendMessage(message);
            span.log("Message has been send");
        }
        finally {
            span.finish();
        }
    }

    private String prepareMessage(String name) {
        Span span = tracer.buildSpan("prepareMessage").start();
        try(Scope scope = tracer.scopeManager().activate(span)) {
            return String.format(GREETING_MESSAGE, name);
        }
        finally {
            span.finish();
        }
    }

    private void sendMessage(String message) {
        Span span = tracer.buildSpan("sendMessage").start();
        try(Scope scope = tracer.scopeManager().activate(span)) {
            logger.info(message);
        }
        finally {
            span.finish();
        }
    }
}
