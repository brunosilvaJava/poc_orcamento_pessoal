package br.com.controlefinanceiro.domain.wallet;

import br.com.controlefinanceiro.domain.movement.MovementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "overdraft")
    private BigDecimal overdraft;

    @OneToMany(mappedBy = "walletEntity")
    private List<MovementEntity> movementEntityList;

}
