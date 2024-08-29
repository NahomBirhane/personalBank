package miu.edu.eaxmcs425.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank(message = "FirstName cannot be empty")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "LastName cannot be empty")
    @Column(nullable = false)
    private String lastName;

    private String telephone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Customers_Accounts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts = new ArrayList<>();

}
