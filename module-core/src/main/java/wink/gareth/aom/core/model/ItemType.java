package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ItemType {
    @JsonProperty("ITEM")
    ITEM,
    @JsonProperty("TAX")
    TAX,
    @JsonProperty("SERVICE_FEE")
    SERVICE_FEE,
    @JsonProperty("DELIVERY_FEE")
    DELIVERY_FEE
}
