package wink.gareth.aom.api.response;

import wink.gareth.aom.core.model.Order;

import java.util.List;

public record OrdersResponse(List<OrderResponse> orderResponses) {
    public static OrdersResponse from(List<Order> order) {
        return new OrdersResponse(order.stream().map(OrderResponse::new).toList());
    }
}
