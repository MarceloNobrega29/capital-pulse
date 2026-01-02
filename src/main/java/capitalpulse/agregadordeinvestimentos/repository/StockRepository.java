package capitalpulse.agregadordeinvestimentos.repository;

import capitalpulse.agregadordeinvestimentos.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
