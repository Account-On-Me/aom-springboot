package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(value = { "target", "source" })
@Document
public class Order {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id = new ObjectId();

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime date = LocalDateTime.now();

    @JsonSerialize(using = ToStringSerializer.class)
    private OrderType type;

    private List<OrderItem> items;

    @DocumentReference(lazy = true)
    private Account payer;

    @DocumentReference(lazy = true)
    private List<Account> candidates = new ArrayList<>();

    private List<Paycheck> paychecksTotal = new ArrayList<>();

    public Order(String id) {
        this.id = new ObjectId(id);
    }

    public Order(String type, Account payer, List<OrderItem> items, List<Account> candidates) {
        this.type = OrderType.valueOf(type);
        this.payer = payer;
        this.items = items;
        this.candidates = candidates;
    }

    @PersistenceCreator
    public Order(ObjectId id, LocalDateTime date, OrderType type, List<OrderItem> items, Account payer, List<Account> candidates, List<Paycheck> paychecksTotal) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.payer = payer;
        this.items = items;
        this.candidates = candidates;
        this.paychecksTotal = paychecksTotal;
    }


    public void computePaychecksTotal() {
        List<Paycheck> result = new ArrayList<>();
        Map<Account, Float> candidateToMoney = new HashMap<>();
        for (OrderItem item : items) {
            for (Paycheck itemPaycheck : item.getPaychecks()) {
                Account candidate = itemPaycheck.getCandidate();
                if (!candidateToMoney.containsKey(candidate)){
                    candidateToMoney.put(candidate, 0f);
                }
                candidateToMoney.put(candidate, candidateToMoney.get(candidate) + itemPaycheck.getShouldPay());
            }
        }

        for (Map.Entry<Account, Float> entry : candidateToMoney.entrySet()) {
            result.add(new Paycheck(
                    // round to 2 decimal places
                    entry.getKey(),
                    Math.round(entry.getValue() * 100.0) / 100.0f
            ));
        }
        this.paychecksTotal = result;
    }
}
