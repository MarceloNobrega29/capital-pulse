package capitalpulse.agregadordeinvestimentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_stock")
public class Stock {

    @Id
    private String stockId;

    @Column(name = "description")
    private String description;

}
