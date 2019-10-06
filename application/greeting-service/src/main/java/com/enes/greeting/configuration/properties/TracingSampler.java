package com.enes.greeting.configuration.properties;

import java.util.function.Function;

public enum TracingSampler {

    //@formatter:off
    CONSTANT("const", TracingProperties.Sampler::getConstant),
    PROBABILISTIC("probabilistic", TracingProperties.Sampler::getProbabilistic),
    RATELIMITING("ratelimiting", TracingProperties.Sampler::getRateLimiting);
    //@formatter:on

    private final String type;

    private final Function<TracingProperties.Sampler, Number> param;

    TracingSampler(String type, Function<TracingProperties.Sampler, Number> param) {
        this.type = type;
        this.param = param;
    }

    public String getType() {
        return type;
    }

    public Number getParam(TracingProperties.Sampler samplerProps) {
        return param.apply(samplerProps);
    }
}

