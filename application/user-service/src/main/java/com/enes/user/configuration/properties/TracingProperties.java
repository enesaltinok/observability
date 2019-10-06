package com.enes.user.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tracing")
public class TracingProperties {

    private String serviceName;

    private String host;

    private Integer udpPort;

    private Boolean logSpans;

    private Sampler sampler;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(Integer udpPort) {
        this.udpPort = udpPort;
    }

    public Boolean getLogSpans() {
        return logSpans;
    }

    public void setLogSpans(Boolean logSpans) {
        this.logSpans = logSpans;
    }

    public Sampler getSampler() {
        return sampler;
    }

    public void setSampler(Sampler sampler) {
        this.sampler = sampler;
    }

    public static class Sampler {

        private String type;

        private Integer constant;

        private Float probabilistic;

        private Float rateLimiting;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getConstant() {
            return constant;
        }

        public void setConstant(Integer constant) {
            this.constant = constant;
        }

        public Float getProbabilistic() {
            return probabilistic;
        }

        public void setProbabilistic(Float probabilistic) {
            this.probabilistic = probabilistic;
        }

        public Float getRateLimiting() {
            return rateLimiting;
        }

        public void setRateLimiting(Float rateLimiting) {
            this.rateLimiting = rateLimiting;
        }
    }
}

