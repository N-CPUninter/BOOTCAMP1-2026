package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Usuario;
import br.uninter.medalerta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
    }

    public Usuario atualizar(Integer id, Usuario novoUsuario) {
        Usuario existente = buscarPorId(id);

        existente.setNome(novoUsuario.getNome());
        existente.setTelefone(novoUsuario.getTelefone());
        existente.setEmail(novoUsuario.getEmail());
        existente.setEnderecoRua(novoUsuario.getEnderecoRua());
        existente.setEnderecoNumero(novoUsuario.getEnderecoNumero());
        existente.setEnderecoComplemento(novoUsuario.getEnderecoComplemento());
        existente.setEnderecoBairro(novoUsuario.getEnderecoBairro());
        existente.setEnderecoCEP(novoUsuario.getEnderecoCEP());
        existente.setEnderecoCidade(novoUsuario.getEnderecoCidade());
        existente.setEnderecoEstado(novoUsuario.getEnderecoEstado());

        return repository.save(existente);
    }

    public void deletar(Integer id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }
}