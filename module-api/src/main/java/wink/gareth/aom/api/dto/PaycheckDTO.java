package wink.gareth.aom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wink.gareth.aom.core.model.Paycheck;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaycheckDTO {
    private String candidate;

    private float shouldPay;

    public Paycheck toPaycheck() {
        return new Paycheck(candidate, shouldPay);
    }

}
