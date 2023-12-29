package wink.gareth.aom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRecordDTO {

    private float amount;

    private String to;
}
