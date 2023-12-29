package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Getter
@Setter
@JsonIgnoreProperties(value = { "target", "source" })
public class Paycheck {
    @DocumentReference(lazy = true)
    private Account candidate;

    private float shouldPay;

    public Paycheck(String candidateId, float shouldPay) {
        this.candidate = new Account(candidateId);
        this.shouldPay = shouldPay;
    }

    @PersistenceCreator
    public Paycheck(Account candidate, float shouldPay) {
        this.candidate = candidate;
        this.shouldPay = shouldPay;
    }

}
