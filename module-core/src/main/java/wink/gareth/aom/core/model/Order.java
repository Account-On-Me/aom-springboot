package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document
public class Order {
    @MongoId
    private ObjectId id = new ObjectId();

    private LocalDateTime date = LocalDateTime.now();

    private OrderType type;

    private List<Account> payer;

    private List<Paycheck> paychecksTotal;
}
