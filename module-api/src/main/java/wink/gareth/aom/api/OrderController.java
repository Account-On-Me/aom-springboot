package wink.gareth.aom.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wink.gareth.aom.api.dto.OrderDTO;
import wink.gareth.aom.api.response.AbstractOrdersResponse;
import wink.gareth.aom.api.response.OrderResponse;
import wink.gareth.aom.api.response.OrdersResponse;
import wink.gareth.aom.core.model.Order;
import wink.gareth.aom.core.service.AccountService;
import wink.gareth.aom.core.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private static final String BASE_URL = "/api/order";

    private final OrderService orderService;

    private final AccountService accountService;

    @GetMapping(BASE_URL + "/abslist")
    public AbstractOrdersResponse getAllAbstractOrderInfo() throws Exception {
        return AbstractOrdersResponse.from(orderService.getAllAbstractOrderInfo());
    }

    @GetMapping(BASE_URL + "/list")
    public List<Order> getAllOrders() throws Exception {
        return orderService.getAllOrders();
    }

    @PostMapping(BASE_URL + "/create")
    public Order createOrder(@RequestBody OrderDTO orderDTO) throws Exception{
        Order order = orderDTO.toOrder();
        return orderService.createOrder(order);
    }

    @GetMapping(BASE_URL + "/{id}")
    public Order getOrderById(@PathVariable String id) throws Exception{
        return orderService.getOrderById(id);
    }

    @DeleteMapping(BASE_URL + "/{id}")
    public void deleteOrderById(@PathVariable String id) throws Exception{
        orderService.deleteOrderById(id);
    }

    @GetMapping(BASE_URL + "/test")
    public Order test() throws Exception{
        return orderService.test();
    }
}
