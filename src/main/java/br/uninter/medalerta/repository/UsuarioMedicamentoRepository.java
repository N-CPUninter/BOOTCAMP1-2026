package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.UsuarioMedicamento;
import br.uninter.medalerta.model.UsuarioMedicamentoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamento, UsuarioMedicamentoId> {
    List<UsuarioMedicamento> findByUsuario_IdUsuario(Integer idUsuario);
    List<UsuarioMedicamento> findByMedicamento_IdMedicamento(Integer idMedicamento);
}