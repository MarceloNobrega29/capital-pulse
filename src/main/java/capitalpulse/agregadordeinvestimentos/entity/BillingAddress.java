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
@Table(name = "tb_billingaddress")
public class BillingAddress {

    @Id
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

}
