package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceCardDetailVO;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    @Query("select cc from CreditCardEntity cc " +
            "inner join cc.walletEntity w ")
    @Override
    List<CreditCardEntity> findAll();

    @Query("select new br.com.controlefinanceiro.domain.creditCard.InvoiceCardVO(m.invoiceEntity.id, pm.description, m.dateDue, sum(m.valuePayment), m.status) " +
            " from MovementEntity m " +
            " inner join m.operation o " +
            " inner join o.paymentMethod pm " +
            " where pm.paymentMethodType like 'CREDIT_CARD' " +
            " and pm.id = :idPaymentMethod " +
            " and (m.status = :status or :status is null) " +
            " and ((EXTRACT(MONTH FROM m.dateDue) = :month or :month is null) " +
            " and (EXTRACT(YEAR FROM m.dateDue) = :year or :year is null)) " +
            " group by m.invoiceEntity.id, m.dateDue, m.status")
    List<InvoiceCardVO> findInvoices(@Param("idPaymentMethod") Long idPaymentMethod,
                                     @Param("status") StatusPaymentType statusPaymentType,
                                     @Param("month") Integer month,
                                     @Param("year") Integer year);

    @Query("select new br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceCardDetailVO(o.dateBuy, o.description, m.valuePayment) " +
            " from MovementEntity m " +
            " inner join m.operation o " +
            " inner join o.paymentMethod pm " +
            " where pm.paymentMethodType like 'CREDIT_CARD' " +
            " and pm.id = :idPaymentMethod " +
            " and m.invoiceEntity.id = :idInvoice ")
    List<InvoiceCardDetailVO> findInvoiceCardDetailById(@Param("idPaymentMethod") Long idPaymentMethod, @Param("idInvoice") Long idInvoice);

    @Modifying
    @Transactional
    @Query("update MovementEntity set status = 'PAID_OUT' where invoiceEntity.id = :idInvoice ")
    void paymentInvoice(@Param("idInvoice") Long idInvoice);
}
