package wink.gareth.aom.api.response;

import wink.gareth.aom.core.model.AbstractOrderInfo;

import java.util.List;

public record AbstractOrdersResponse(List<AbstractOrderResponse> abstractOrderResponses) {
    public static AbstractOrdersResponse from(List<AbstractOrderInfo> abstractOrderInfoList) {
        return new AbstractOrdersResponse(abstractOrderInfoList.stream().map(AbstractOrderResponse::new).toList());
    }
}
