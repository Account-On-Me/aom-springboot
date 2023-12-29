package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ItemPayMethod {
    @JsonProperty("EQUAL")
    EQUAL,
    @JsonProperty("RATIO")
    RATIO,
    @JsonProperty("MANUAL")
    MANUAL
}
