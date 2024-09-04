package miu.edu.eaxmcs425.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import miu.edu.eaxmcs425.exception.CustomerNotFoundException;
import miu.edu.eaxmcs425.model.Account;
import miu.edu.eaxmcs425.model.Customer;
import miu.edu.eaxmcs425.repository.AccountRepository;
import miu.edu.eaxmcs425.repository.CustomerRepository;
import miu.edu.eaxmcs425.service.AccountService;
import miu.edu.eaxmcs425.service.dtos.requestdto.AccountRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountWithCustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerWithAccounts createAccount(AccountRequest accountRequest, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                                              .orElseThrow(() -> new CustomerNotFoundException("Customer not found by Id: "+customerId));

        Account account = Account.builder()
                                 .accountNumber(accountRequest.getAccountNumber())
                                 .dateOpened(accountRequest.getDateOpened())
                                 .status(accountRequest.getStatus())
                                 .balance(accountRequest.getBalance())
                                 .build();

        // Initialize the customers list if it's null
        if (account.getCustomers() == null) {
            account.setCustomers(new ArrayList<>());
        }

        // Add the customer to the account's customer list
        account.getCustomers().add(customer);

        // Add the account to the customer's account list
        if (customer.getAccounts() == null) {
            customer.setAccounts(new ArrayList<>());
        }

        customer.getAccounts().add(account);

        // Save the account and the customer
        accountRepository.save(account); // Save the account
        customerRepository.save(customer); // Save the customer

        // Convert account to AccountResponse
        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);

        // Return the customer with the account
        return CustomerWithAccounts.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .telephone(customer.getTelephone())
                .accountResponses(Collections.singletonList(accountResponse)) // Wrap in a list
                .build();
    }

    @Override
    public List<AccountWithCustomerResponse> findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(int years, String status) {
        List<Account> accounts = accountRepository.findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(years, status);

        return accounts.stream()
                .map(account -> {
                    AccountWithCustomerResponse response = modelMapper.map(account, AccountWithCustomerResponse.class);
                    response.setCustomerResponses(account.getCustomers().stream()
                            .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                            .collect(Collectors.toList()));
                    return response;
                })
                .collect(Collectors.toList());
    }


}
