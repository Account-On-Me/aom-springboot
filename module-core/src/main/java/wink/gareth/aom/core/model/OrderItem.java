package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = { "target", "source" })
public class OrderItem {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id = new ObjectId();

    private String name;

    private String thumbnail;

    @JsonSerialize(using = ToStringSerializer.class)
    private ItemType type;

    @JsonSerialize(using = ToStringSerializer.class)
    private ItemPayMethod method;

    private float price;

    private int quantity;

    private boolean taxed;

    private List<Paycheck> paychecks = new ArrayList<>();

    public OrderItem(String id) {
        this.id = new ObjectId(id);
    }

    public OrderItem(String name, String thumbnail, String type, String method, float price, int quantity, boolean taxed, List<Paycheck> paychecks) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.type = ItemType.valueOf(type);
        this.method = ItemPayMethod.valueOf(method);
        this.price = price;
        this.quantity = quantity;
        this.taxed = taxed;
        this.paychecks = paychecks;
    }

    @PersistenceCreator
    public OrderItem(ObjectId id, String name, String thumbnail, ItemType type, ItemPayMethod method, float price, int quantity, boolean taxed, List<Paycheck> paychecks) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.type = type;
        this.method = method;
        this.price = price;
        this.quantity = quantity;
        this.taxed = taxed;
        this.paychecks = paychecks;
    }
}
