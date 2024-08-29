package miu.edu.eaxmcs425.service;

import miu.edu.eaxmcs425.model.Customer;
import miu.edu.eaxmcs425.service.dtos.requestdto.CustomerRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerWithAccounts findCustomerById(Integer id);

}
