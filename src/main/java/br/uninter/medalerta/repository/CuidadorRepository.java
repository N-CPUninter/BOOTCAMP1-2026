package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.Cuidador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer>{
    Optional<Cuidador> findByUsuario_IdUsuario(Integer idUsuario);
}
