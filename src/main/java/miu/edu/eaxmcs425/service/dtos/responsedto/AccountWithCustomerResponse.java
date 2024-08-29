package miu.edu.eaxmcs425.service.dtos.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountWithCustomerResponse {

    private Long accountId;
    private String accountNumber;
    private LocalDate dateOpened;
    private String status;
    private Double balance;
    private List<CustomerResponse> customerResponses;

}
