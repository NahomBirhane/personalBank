package miu.edu.eaxmcs425.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import miu.edu.eaxmcs425.service.CustomerService;
import miu.edu.eaxmcs425.service.dtos.requestdto.CustomerRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cs425/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerWithAccounts findCustomerById(@PathVariable("id") Integer id){
        return customerService.findCustomerById(id);
    }

}
