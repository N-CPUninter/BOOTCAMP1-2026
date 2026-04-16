package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Medicamento;
import br.uninter.medalerta.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    public Medicamento salvar(Medicamento Medicamento) {
        return repository.save(Medicamento);
    }

    public List<Medicamento> listarTodos() {
        return repository.findAll();
    }

    public Medicamento buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado: " + id));
    }

    public Medicamento atualizar(Integer id, Medicamento novoMedicamento) {
        Medicamento existente = buscarPorId(id);

        existente.setNomeComercial(novoMedicamento.getNomeComercial());
        existente.setNomeGenerico(novoMedicamento.getNomeGenerico());
        existente.setQuantidade(novoMedicamento.getQuantidade());
        existente.setFormaUso(novoMedicamento.getFormaUso());
        existente.setObservacao(novoMedicamento.getObservacao());

        return repository.save(existente);
    }

    public void deletar(Integer id) {
        Medicamento Medicamento = buscarPorId(id);
        repository.delete(Medicamento);
    }
}