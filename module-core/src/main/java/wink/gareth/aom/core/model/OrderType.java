package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderType {
    @JsonProperty("DEFAULT")
    DEFAULT,
    @JsonProperty("WALMART")
    WALMART,
    @JsonProperty("INTERNET")
    INTERNET,
    @JsonProperty("ENERGY")
    ENERGY,
    @JsonProperty("TAKEOUT")
    TAKEOUT
}
