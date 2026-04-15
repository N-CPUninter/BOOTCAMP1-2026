package br.uninter.medalerta.service;

import br.uninter.medalerta.model.*;
import br.uninter.medalerta.repository.MedicamentoRepository;
import br.uninter.medalerta.repository.UsuarioMedicamentoRepository;
import br.uninter.medalerta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class UsuarioMedicamentoService {

    private final UsuarioMedicamentoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final MedicamentoRepository medicamentoRepository;

    public UsuarioMedicamentoService(
            UsuarioMedicamentoRepository repository,
            UsuarioRepository usuarioRepository,
            MedicamentoRepository medicamentoRepository
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public UsuarioMedicamento vincular(
            Integer idUsuario,
            Integer idMedicamento,
            LocalTime horarioUso,
            String frequenciaUso,
            String dosagem,
            LocalDateTime dataHorarioAlerta,
            StatusAlerta statusAlerta,
            LocalDateTime dataHorarioConsumo,
            ConfirmacaoConsumo confirmacaoConsumo
    ) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + idUsuario));

        Medicamento Medicamento = medicamentoRepository.findById(idMedicamento)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado: " + idMedicamento));

        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);

        if (repository.existsById(id)) {
            throw new RuntimeException("Esse vínculo usuário-Medicamento já existe.");
        }

        UsuarioMedicamento entidade = new UsuarioMedicamento();
        entidade.setId(id);
        entidade.setUsuario(usuario);
        entidade.setMedicamento(Medicamento);
        entidade.setHorarioUso(horarioUso);
        entidade.setFrequenciaUso(frequenciaUso);
        entidade.setDosagem(dosagem);
        entidade.setDataHorarioAlerta(dataHorarioAlerta);
        entidade.setStatusAlerta(statusAlerta);
        entidade.setDataHorarioConsumo(dataHorarioConsumo);
        entidade.setConfirmacaoConsumo(confirmacaoConsumo);

        return repository.save(entidade);
    }

    public List<UsuarioMedicamento> listarTodos() {
        return repository.findAll();
    }

    public List<UsuarioMedicamento> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuario_IdUsuario(idUsuario);
    }

    public UsuarioMedicamento buscarPorId(Integer idUsuario, Integer idMedicamento) {
        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado."));
    }

    public UsuarioMedicamento atualizar(
            Integer idUsuario,
            Integer idMedicamento,
            LocalTime horarioUso,
            String frequenciaUso,
            String dosagem,
            LocalDateTime dataHorarioAlerta,
            StatusAlerta statusAlerta,
            LocalDateTime dataHorarioConsumo,
            ConfirmacaoConsumo confirmacaoConsumo
    ) {
        UsuarioMedicamento existente = buscarPorId(idUsuario, idMedicamento);

        existente.setHorarioUso(horarioUso);
        existente.setFrequenciaUso(frequenciaUso);
        existente.setDosagem(dosagem);
        existente.setDataHorarioAlerta(dataHorarioAlerta);
        existente.setStatusAlerta(statusAlerta);
        existente.setDataHorarioConsumo(dataHorarioConsumo);
        existente.setConfirmacaoConsumo(confirmacaoConsumo);

        return repository.save(existente);
    }

    public void remover(Integer idUsuario, Integer idMedicamento) {
        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);

        if (!repository.existsById(id)) {
            throw new RuntimeException("Vínculo não encontrado.");
        }

        repository.deleteById(id);
    }
}