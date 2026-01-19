package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.dto.AssociateAccountStockDto;
import capitalpulse.agregadordeinvestimentos.entity.Account;
import capitalpulse.agregadordeinvestimentos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Account> assosiateStock(@PathVariable("accountId") String accountId,
                                                 @RequestBody AssociateAccountStockDto dto) {
        accountService.assosiateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }

}