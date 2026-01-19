package capitalpulse.agregadordeinvestimentos.service;

import capitalpulse.agregadordeinvestimentos.dto.AssociateAccountStockDto;
import capitalpulse.agregadordeinvestimentos.entity.Account;
import capitalpulse.agregadordeinvestimentos.entity.AccountStock;
import capitalpulse.agregadordeinvestimentos.entity.AccountStockId;
import capitalpulse.agregadordeinvestimentos.entity.Stock;
import capitalpulse.agregadordeinvestimentos.repository.AccountRepository;
import capitalpulse.agregadordeinvestimentos.repository.AccountStockRepository;
import capitalpulse.agregadordeinvestimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;


    public void assosiateStock(String accountId, AssociateAccountStockDto dto) {

        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        Stock stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));


        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);
    }
}
