package wink.gareth.aom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wink.gareth.aom.core.model.Account;
import wink.gareth.aom.core.model.Order;
import wink.gareth.aom.core.model.OrderItem;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;

    private String type;

    private String payer;

    private List<OrderItemDTO> items;

    private List<String> candidates;

    public Order toOrder() {
        List<OrderItem> items = this.items.stream().map(OrderItemDTO::toOrderItem).toList();
        return new Order(
                type,
                new Account(payer),
                items,
                candidates.stream().map(Account::new).toList()
        );
    }
}
