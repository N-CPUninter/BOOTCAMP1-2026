package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
