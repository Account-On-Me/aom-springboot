package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AbstractOrderInfo {

    private String orderId;

    private LocalDateTime date;

    private OrderType type;

    private String payer;

    public AbstractOrderInfo(Order order) {
        this.orderId = order.getId().toString();
        this.date = order.getDate();
        this.type = order.getType();
        this.payer = order.getPayer().getName();
    }
}
