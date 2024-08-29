package miu.edu.eaxmcs425.service.Impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import miu.edu.eaxmcs425.model.Customer;
import miu.edu.eaxmcs425.repository.AccountRepository;
import miu.edu.eaxmcs425.repository.CustomerRepository;
import miu.edu.eaxmcs425.service.AccountService;
import miu.edu.eaxmcs425.service.CustomerService;
import miu.edu.eaxmcs425.service.dtos.requestdto.CustomerRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        Customer customer = Customer.builder()
                .customerId(null)
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .telephone(customerRequest.getTelephone())
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        return modelMapper.map(savedCustomer, CustomerResponse.class);

    }

    @Override
    public CustomerWithAccounts findCustomerById(Integer id) {

        Customer customer = customerRepository.findById(id)
                                              .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CustomerWithAccounts customerWithAccounts = modelMapper.map(customer, CustomerWithAccounts.class);

        List<AccountResponse> accountResponses = customer.getAccounts()
                                                         .stream()
                                                         .map( a -> modelMapper.map(a, AccountResponse.class)).toList();

        customerWithAccounts.setAccountResponses(accountResponses);

        return customerWithAccounts;
    }
}
