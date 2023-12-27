package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    @MongoId
    private final ObjectId id = new ObjectId();

    private String name;

    private String thumbnail;

    private ItemType type;

    private ItemPayMethod method;

    private float price;

    private int quantity;

    private boolean taxed;

    private List<Paycheck> paychecks;
}
