package com.enes.greeting.filters;

import io.opentracing.Tracer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DistributedTracingFilter implements Filter {

    private final Tracer tracer;

    public DistributedTracingFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if(tracer.activeSpan() != null) {
            String traceId = tracer.activeSpan().context().toTraceId();
            ((HttpServletResponse) servletResponse).addHeader("X-B3-TraceId", traceId);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
