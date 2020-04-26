package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    CreditCardEntity findByPaymentMethodEntity(PaymentMethodEntity paymentMethodEntity);

    /*@Query("select new InvoiceCardVO(py.description, m.date_due, sum(m.value_payment)) " +
            "from MovementEntity m " +
            "inner join m.operation o " +
            "inner join o.paymentMethod pm " +
            "where pm.paymentMethodType like 'CREDIT_CARD' " +
            "group by o.paymentMethod, m.dateDue")
    CreditCardVO find();*/

}
