package wink.gareth.aom.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Paycheck {
    @DocumentReference(lazy = true)
    private List<Account> candidate;

    private float shouldPay;

}
