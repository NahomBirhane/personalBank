package miu.edu.eaxmcs425.service.dtos.requestdto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String telephone;

}
