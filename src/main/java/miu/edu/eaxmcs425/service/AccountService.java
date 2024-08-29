package miu.edu.eaxmcs425.service;

import miu.edu.eaxmcs425.service.dtos.requestdto.AccountRequest;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountWithCustomerResponse;
import miu.edu.eaxmcs425.service.dtos.responsedto.CustomerWithAccounts;

import java.util.List;

public interface AccountService {

    CustomerWithAccounts createAccount(AccountRequest accountRequest, Integer customerId);

    List<AccountWithCustomerResponse> findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(int year, String active);

}
