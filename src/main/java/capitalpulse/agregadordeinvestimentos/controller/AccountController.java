package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.dto.AccountStockResponseDto;
import capitalpulse.agregadordeinvestimentos.dto.AssociateAccountStockDto;
import capitalpulse.agregadordeinvestimentos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> assosiateStock(@PathVariable("accountId") String accountId,
                                                 @RequestBody AssociateAccountStockDto dto) {
        accountService.assosiateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> listStock(@PathVariable("accountId") String accountId) {
        var stocks = accountService.listStock(accountId);
        return ResponseEntity.ok(stocks);
    }
}