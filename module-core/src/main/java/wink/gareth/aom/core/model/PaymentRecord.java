package wink.gareth.aom.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(value = { "target", "source" })
public class PaymentRecord {
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime date = LocalDateTime.now();

    private float amount;

    @DocumentReference(lazy = true)
    private Account to;

    public PaymentRecord(float amount, Account account) {
        this.amount =  amount;
        this.to = account;
    }
}
