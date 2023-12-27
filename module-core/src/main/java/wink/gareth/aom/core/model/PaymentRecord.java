package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRecord {
    private final LocalDateTime date = LocalDateTime.now();

    private float amount;

    @DocumentReference(lazy = true)
    private Account to;
}
