package com.enes.greeting.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.DefaultSink;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.StatusAtLeastStrategy;
import org.zalando.logbook.json.JsonHttpLogFormatter;
import org.zalando.logbook.json.PrettyPrintingJsonBodyFilter;

import java.util.Collections;

import static org.zalando.logbook.HeaderFilters.removeHeaders;
import static org.zalando.logbook.HeaderFilters.replaceHeaders;
import static org.zalando.logbook.json.JsonBodyFilters.replaceJsonStringProperty;

@Configuration
public class LoggingConfiguration {

    @Bean
    public Logbook logbook() {

        return Logbook.builder()
                      .strategy(new StatusAtLeastStrategy(400))
                      .bodyFilter(new PrettyPrintingJsonBodyFilter())

                      //Do not log user passwords
                      .bodyFilter(replaceJsonStringProperty(Collections.singleton("password"), "***"))

                      //Do not log authorization token and key headers
                      .headerFilter(replaceHeaders("X-Api-Key"::equalsIgnoreCase, "***"))
                      .headerFilter(replaceHeaders("Authorization"::equalsIgnoreCase, "***"))

                      //Remove headers which have fixed values thus not needed to log
                      .headerFilter(removeHeaders("Accept"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Accept-Encoding"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Accept-Language"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Connection"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Content-Length"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Content-Type"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Access-Control-Allow-Headers"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Access-Control-Allow-Methods"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Access-Control-Allow-Origin"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Access-Control-Expose-Headers"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Cache-Control"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Date"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Expires"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Pragma"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("Transfer-Encoding"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("X-Content-Type-Options"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("X-Frame-Options"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("X-Powered-By"::equalsIgnoreCase))
                      .headerFilter(removeHeaders("X-XSS-Protection"::equalsIgnoreCase))

                      .sink(new DefaultSink(new JsonHttpLogFormatter(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)),
                                            new DefaultHttpLogWriter()))
                      .build();
    }
}
