package br.com.controlefinanceiro.domain.recurrent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurrentRepository extends JpaRepository<RecurrentEntity, Long> {

}
