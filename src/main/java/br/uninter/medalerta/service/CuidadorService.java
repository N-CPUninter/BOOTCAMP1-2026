package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Cuidador;
import br.uninter.medalerta.repository.CuidadorRepository;
import br.uninter.medalerta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuidadorService {

    private final CuidadorRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CuidadorService(CuidadorRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Cuidador salvar(Cuidador cuidador){
        Integer idUsuario = cuidador.getUsuario() != null ? cuidador.getUsuario().getIdUsuario() : null;

        if (idUsuario == null) {
            throw new RuntimeException("Usuário do cuidador é obrigatório.");
        }

        var usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + idUsuario));

        if (repository.findByUsuario_IdUsuario(idUsuario).isPresent()) {
            throw new RuntimeException("Já existe um cuidador vinculado ao usuário: " + idUsuario);
        }

        cuidador.setUsuario(usuario);
        return repository.save(cuidador);
    }

    public List<Cuidador> listarTodos() {
        return repository.findAll();
    }

    public Cuidador buscarPorId(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuidador não encontrado: " + id));
    }

    public Cuidador buscarPorUsuarioId(Integer idUsuario) {
        return repository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Cuidador não encontrado para o usuário: " + idUsuario));
    }

    public Cuidador atualizar(Integer id, Cuidador novoCuidador){
        Cuidador existente = buscarPorId(id);

        existente.setNome(novoCuidador.getNome());
        existente.setTelefone(novoCuidador.getTelefone());
        existente.setEmail(novoCuidador.getEmail());
        existente.setEnderecoRua(novoCuidador.getEnderecoRua());
        existente.setEnderecoNumero(novoCuidador.getEnderecoNumero());
        existente.setEnderecoComplemento(novoCuidador.getEnderecoComplemento());
        existente.setEnderecoBairro(novoCuidador.getEnderecoBairro());
        existente.setEnderecoCEP(novoCuidador.getEnderecoCEP());
        existente.setEnderecoCidade(novoCuidador.getEnderecoCidade());
        existente.setEnderecoEstado(novoCuidador.getEnderecoEstado());

        return repository.save(existente);
    }

    public void deletar(Integer id){
        Cuidador cuidador = buscarPorId(id);
        repository.delete(cuidador);
    }
}
