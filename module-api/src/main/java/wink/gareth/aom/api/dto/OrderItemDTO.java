package wink.gareth.aom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wink.gareth.aom.core.model.OrderItem;
import wink.gareth.aom.core.model.Paycheck;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
    private String id;

    private String name;

    private String thumbnail;

    private String type;

    private String method;

    private float price;

    private int quantity;

    private boolean taxed;

    private List<PaycheckDTO> paychecks;

    public OrderItem toOrderItem() {
        List<Paycheck> realPaychecks = paychecks.stream().map(PaycheckDTO::toPaycheck).toList();
        return new OrderItem(
                name,
                thumbnail,
                type,
                method,
                price,
                quantity,
                taxed,
                realPaychecks
        );
    }
}
