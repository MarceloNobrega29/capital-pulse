package capitalpulse.agregadordeinvestimentos.repository;

import capitalpulse.agregadordeinvestimentos.entity.AccountStock;
import capitalpulse.agregadordeinvestimentos.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
