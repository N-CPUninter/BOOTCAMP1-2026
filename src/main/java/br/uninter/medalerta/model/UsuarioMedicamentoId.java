package br.uninter.medalerta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioMedicamentoId implements Serializable {

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idMedicamento")
    private Integer idMedicamento;

    public UsuarioMedicamentoId() {
    }

    public UsuarioMedicamentoId(Integer idUsuario, Integer idMedicamento) {
        this.idUsuario = idUsuario;
        this.idMedicamento = idMedicamento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioMedicamentoId that)) return false;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(idMedicamento, that.idMedicamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idMedicamento);
    }
}