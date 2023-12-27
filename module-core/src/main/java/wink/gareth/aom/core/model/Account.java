package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Account {
    @MongoId
    private final ObjectId id = new ObjectId();

    @Indexed(unique = true)
    private String name;

    private String avatar;

    private String email;

    private List<PaymentRecord> paymentRecords;

    private List<Paycheck> remainingPaychecks;

}
