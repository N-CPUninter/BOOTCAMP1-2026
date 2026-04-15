package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedicamento")
    private Integer idMedicamento;

    @Column(nullable = false, length = 100)
    private String nomeComercial;

    @Column(length = 100)
    private String nomeGenerico;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private QuantidadeTipo quantidade;

    @Column(length = 100)
    private String formaUso;

    @Column(length = 200)
    private String observacao;

    @OneToMany(mappedBy = "Medicamento", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<UsuarioMedicamento> usuarioMedicamentos = new ArrayList<>();

    public Medicamento() {
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public String getNomeGenerico() {
        return nomeGenerico;
    }

    public void setNomeGenerico(String nomeGenerico) {
        this.nomeGenerico = nomeGenerico;
    }

    public QuantidadeTipo getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(QuantidadeTipo quantidade) {
        this.quantidade = quantidade;
    }

    public String getFormaUso() {
        return formaUso;
    }

    public void setFormaUso(String formaUso) {
        this.formaUso = formaUso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<UsuarioMedicamento> getUsuarioMedicamentos() {
        return usuarioMedicamentos;
    }

    public void setUsuarioMedicamentos(List<UsuarioMedicamento> usuarioMedicamentos) {
        this.usuarioMedicamentos = usuarioMedicamentos;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMedicamento=" + idMedicamento +
                ", nomeComercial='" + nomeComercial + '\'' +
                ", nomeGenerico='" + nomeGenerico + '\'' +
                ", quantidade=" + quantidade +
                ", formaUso='" + formaUso + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}