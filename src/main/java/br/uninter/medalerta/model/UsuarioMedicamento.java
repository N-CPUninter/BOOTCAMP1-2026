package br.uninter.medalerta.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "UsuarioMedicamento")
public class UsuarioMedicamento {

    @EmbeddedId
    private UsuarioMedicamentoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario Usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMedicamento")
    @JoinColumn(name = "idMedicamento")
    private Medicamento Medicamento;

    @Column(nullable = false)
    private LocalTime horarioUso;

    @Column(length = 50)
    private String frequenciaUso;

    @Column(nullable = false, length = 50)
    private String dosagem;

    @Column(nullable = false)
    private LocalDateTime dataHorarioAlerta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAlerta statusAlerta;

    private LocalDateTime dataHorarioConsumo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ConfirmacaoConsumo confirmacaoConsumo;

    public UsuarioMedicamento() {
    }

    public UsuarioMedicamentoId getId() {
        return id;
    }

    public void setId(UsuarioMedicamentoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.Usuario = usuario;
    }

    public Medicamento getMedicamento() {
        return Medicamento;
    }

    public void setMedicamento(Medicamento Medicamento) {
        this.Medicamento = Medicamento;
    }

    public LocalTime getHorarioUso() {
        return horarioUso;
    }

    public void setHorarioUso(LocalTime horarioUso) {
        this.horarioUso = horarioUso;
    }

    public String getFrequenciaUso() {
        return frequenciaUso;
    }

    public void setFrequenciaUso(String frequenciaUso) {
        this.frequenciaUso = frequenciaUso;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public LocalDateTime getDataHorarioAlerta() {
        return dataHorarioAlerta;
    }

    public void setDataHorarioAlerta(LocalDateTime dataHorarioAlerta) {
        this.dataHorarioAlerta = dataHorarioAlerta;
    }

    public StatusAlerta getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(StatusAlerta statusAlerta) {
        this.statusAlerta = statusAlerta;
    }

    public LocalDateTime getDataHorarioConsumo() {
        return dataHorarioConsumo;
    }

    public void setDataHorarioConsumo(LocalDateTime dataHorarioConsumo) {
        this.dataHorarioConsumo = dataHorarioConsumo;
    }

    public ConfirmacaoConsumo getConfirmacaoConsumo() {
        return confirmacaoConsumo;
    }

    public void setConfirmacaoConsumo(ConfirmacaoConsumo confirmacaoConsumo) {
        this.confirmacaoConsumo = confirmacaoConsumo;
    }

    @Override
    public String toString() {
        return "UsuarioMedicamento{" +
                "idUsuario=" + (id != null ? id.getIdUsuario() : null) +
                ", idMedicamento=" + (id != null ? id.getIdMedicamento() : null) +
                ", usuario=" + (Usuario != null ? Usuario.getNome() : null) +
                ", Medicamento=" + (Medicamento != null ? Medicamento.getNomeComercial() : null) +
                ", horarioUso=" + horarioUso +
                ", frequenciaUso='" + frequenciaUso + '\'' +
                ", dosagem='" + dosagem + '\'' +
                ", dataHorarioAlerta=" + dataHorarioAlerta +
                ", statusAlerta=" + statusAlerta +
                ", dataHorarioConsumo=" + dataHorarioConsumo +
                ", confirmacaoConsumo=" + confirmacaoConsumo +
                '}';
    }
}