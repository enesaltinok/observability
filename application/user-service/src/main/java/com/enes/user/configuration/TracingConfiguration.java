package com.enes.user.configuration;

import com.enes.user.configuration.properties.TracingProperties;
import com.enes.user.configuration.properties.TracingSampler;
import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class TracingConfiguration {

    @Bean
    public Tracer tracer(TracingProperties properties) {

        String serviceName = properties.getServiceName();
        String host = properties.getHost();
        Integer udpPort = properties.getUdpPort();
        Boolean log = properties.getLogSpans();
        TracingProperties.Sampler samplerProps = properties.getSampler();

        Configuration.SenderConfiguration senderConfiguration = new Configuration.SenderConfiguration().withAgentHost(host).withAgentPort(udpPort);
        Configuration.ReporterConfiguration reporterConfiguration = new Configuration.ReporterConfiguration().withLogSpans(log)
                                                                                                             .withSender(senderConfiguration);

        TracingSampler sampler = TracingSampler.valueOf(samplerProps.getType().toUpperCase());
        Configuration.SamplerConfiguration samplerConfiguration = new Configuration.SamplerConfiguration().withType(sampler.getType())
                                                                                                          .withParam(sampler.getParam(samplerProps));

        return new Configuration(serviceName).withReporter(reporterConfiguration).withSampler(samplerConfiguration).getTracer();
    }
}
