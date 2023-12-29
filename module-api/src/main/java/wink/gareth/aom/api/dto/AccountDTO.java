package wink.gareth.aom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String id;

    private String name;

    private String avatar;

    private String email;

    private List<PaymentRecordDTO> paymentRecords;

    private List<PaycheckDTO> remainingPaychecks;

}
