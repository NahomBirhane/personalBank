package miu.edu.eaxmcs425.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import miu.edu.eaxmcs425.service.AccountService;
import miu.edu.eaxmcs425.service.dtos.requestdto.AccountRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountWithCustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cs425/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerWithAccounts createAccount(
            @Valid @RequestBody AccountRequest accountRequest,
            @PathVariable Integer customerId){
        return accountService.createAccount(accountRequest, customerId);
    }
    @GetMapping("/premium")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountWithCustomerResponse> findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(
            @RequestParam("year") int year,
            @RequestParam("active") String active
    ) {
        return accountService.findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(year, active);
    }


}
