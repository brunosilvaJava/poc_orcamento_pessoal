package br.com.controlefinanceiro.domain.recurrent;

import br.com.controlefinanceiro.domain.operation.OperationType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentType;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recurrent")
public class RecurrentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurrent")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "days")
    private String days;

    @ManyToOne
    @JoinColumn(name = "id_payment_method", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_recurrent_type", nullable = false)
    private PaymentRecurrentType type;

    @ManyToOne
    @JoinColumn(name = "id_wallet", nullable = false)
    private WalletEntity wallet;

    public List<Integer> getDays(){
        return stringToList(days);
    }

    public void setDays(List<Integer> days){
        this.days = listToString(days);
    }

    public static String listToString(List<Integer> numbers){
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static List<Integer> stringToList(String text){
        return Arrays.stream(text.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
