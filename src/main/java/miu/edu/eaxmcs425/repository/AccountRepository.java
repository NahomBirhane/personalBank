package miu.edu.eaxmcs425.repository;


import miu.edu.eaxmcs425.model.Account;
import miu.edu.eaxmcs425.model.Customer;
import miu.edu.eaxmcs425.service.dtos.responsedto.AccountWithCustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE YEAR(CURRENT_DATE) - YEAR(a.dateOpened) >= :year AND a.status = :active order by a.balance desc")
    List<Account> findAllAccountsDateCreatedGreaterThanFiveYearsAndActive(@Param("year") int year, @Param("active") String active);
}
