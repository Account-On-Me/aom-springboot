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
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@JsonIgnoreProperties(value = { "target", "source" })
public class Account {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id = new ObjectId();

    @Indexed(unique = true)
    private String name;

    private String avatar;

    private String email;

    private List<PaymentRecord> paymentRecords = new ArrayList<>();

    private List<Paycheck> remainingPaychecks = new ArrayList<>();

    public Account(String id) {
        this.id = new ObjectId(id);
    }

    public Account(String name, String avatar, String email){
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }

    @PersistenceCreator
    public Account(ObjectId id, String name, String avatar, String email, List<PaymentRecord> paymentRecords, List<Paycheck> remainingPaychecks){
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.paymentRecords = paymentRecords;
        this.remainingPaychecks = remainingPaychecks;
    }

    public void deletePaycheck(Paycheck paycheck) {
        remainingPaychecks.remove(paycheck);
    }

    public void addPaycheck(Paycheck paycheck) {
        // if candidate already has a paycheck, add the amount to the existing paycheck
        if (remainingPaychecks.stream().anyMatch(paycheck1 -> paycheck1.getCandidate().getId().equals(paycheck.getCandidate().getId()))) {
            Paycheck existingPaycheck = remainingPaychecks
                    .stream()
                    .filter(paycheck1 -> paycheck1.getCandidate().getId().equals(paycheck.getCandidate().getId()))
                    .findFirst()
                    .get();
            existingPaycheck.setShouldPay(existingPaycheck.getShouldPay() + paycheck.getShouldPay());
        }
        else { // else add this new paycheck
            remainingPaychecks.add(paycheck);
        }
    }

    public void addPaymentRecord(PaymentRecord paymentRecord) {
        paymentRecords.add(paymentRecord);
    }

}
