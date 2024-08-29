package miu.edu.eaxmcs425.service.dtos.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithAccounts {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String telephone;
    private List<AccountResponse> accountResponses;
}

