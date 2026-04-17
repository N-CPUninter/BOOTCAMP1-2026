package br.uninter.medalerta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Prescricao")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrescricao")
    private Integer idPrescricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedicamento", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false)
    private Integer dosagemValor;

    @Column(nullable = false, length = 30)
    private String dosagemUnidade;

    private Integer frequenciaUso;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FrequenciaTipo frequenciaTipo;

    public Prescricao() {
    }

    public Integer getIdPrescricao() {
        return idPrescricao;
    }

    public void setIdPrescricao(Integer idPrescricao) {
        this.idPrescricao = idPrescricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getDosagemValor() {
        return dosagemValor;
    }

    public void setDosagemValor(Integer dosagemValor) {
        this.dosagemValor = dosagemValor;
    }

    public String getDosagemUnidade() {
        return dosagemUnidade;
    }

    public void setDosagemUnidade(String dosagemUnidade) {
        this.dosagemUnidade = dosagemUnidade;
    }

    public Integer getFrequenciaUso() {
        return frequenciaUso;
    }

    public void setFrequenciaUso(Integer frequenciaUso) {
        this.frequenciaUso = frequenciaUso;
    }

    public FrequenciaTipo getFrequenciaTipo() {
        return frequenciaTipo;
    }

    public void setFrequenciaTipo(FrequenciaTipo frequenciaTipo) {
        this.frequenciaTipo = frequenciaTipo;
    }

    @Override
    public String toString() {
        return "Prescricao{" +
                "idPrescricao=" + idPrescricao +
                ", usuario=" + (usuario != null ? usuario.getIdUsuario() : null) +
                ", medicamento=" + (medicamento != null ? medicamento.getIdMedicamento() : null) +
                ", dosagemValor=" + dosagemValor +
                ", dosagemUnidade='" + dosagemUnidade + '\'' +
                ", frequenciaUso=" + frequenciaUso +
                ", frequenciaTipo=" + frequenciaTipo +
                '}';
    }
}
