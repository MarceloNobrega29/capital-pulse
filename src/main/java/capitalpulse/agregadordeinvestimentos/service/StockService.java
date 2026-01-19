package capitalpulse.agregadordeinvestimentos.service;

import capitalpulse.agregadordeinvestimentos.dto.CreateStockDto;
import capitalpulse.agregadordeinvestimentos.dto.CreateUserDto;
import capitalpulse.agregadordeinvestimentos.entity.Stock;
import capitalpulse.agregadordeinvestimentos.entity.User;
import capitalpulse.agregadordeinvestimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;


    public void createStock(CreateStockDto createStockDto) {

            Stock stock = new Stock();
            stock.setStockId(createStockDto.stockId());
            stock.setDescription(createStockDto.description());
            stockRepository.save(stock);
    }
}
