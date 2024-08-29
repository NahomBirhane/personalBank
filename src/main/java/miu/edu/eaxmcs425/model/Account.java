package miu.edu.eaxmcs425.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotBlank(message = "AccountNumber cannot be empty")
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private LocalDate dateOpened;

    @NotBlank(message = "Status cannot be empty")
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double balance;

    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();  // Initialize to avoid NPE
}
