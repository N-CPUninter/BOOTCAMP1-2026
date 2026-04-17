package br.uninter.medalerta.app;

import br.uninter.medalerta.model.*;
import br.uninter.medalerta.service.MedicamentoService;
import br.uninter.medalerta.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Component
public class App implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final MedicamentoService medicamentoService;

    public App(
            UsuarioService usuarioService,
            MedicamentoService medicamentoService
    ) {
        this.usuarioService = usuarioService;
        this.medicamentoService = medicamentoService;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro(sc, "Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> menuUsuario(sc);
                    case 2 -> menuMedicamento(sc);
                    case 0 -> System.out.println("Encerrando aplicação...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        sc.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n===== MEDALERTA - MENU PRINCIPAL =====");
        System.out.println("1 - CRUD Usuário");
        System.out.println("2 - CRUD Medicamento");
        System.out.println("0 - Sair");
    }

    private void menuUsuario(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- CRUD USUÁRIO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Remover");
            System.out.println("0 - Voltar");

            opcao = lerInteiro(sc, "Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarUsuario(sc);
                case 2 -> listarUsuarios();
                case 3 -> buscarUsuarioPorId(sc);
                case 4 -> atualizarUsuario(sc);
                case 5 -> removerUsuario(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuario(Scanner sc) {
        Usuario u = new Usuario();

        System.out.println("\nCadastro de Usuário");
        u.setNome(lerTexto(sc, "Nome: "));
        u.setTelefone(lerTexto(sc, "Telefone: "));
        u.setEmail(lerTexto(sc, "Email: "));
        u.setEnderecoRua(lerTexto(sc, "Rua: "));
        u.setEnderecoNumero(lerInteiroOpcional(sc, "Número (vazio para null): "));
        u.setEnderecoComplemento(lerTextoOpcional(sc, "Complemento (vazio para null): "));
        u.setEnderecoBairro(lerTextoOpcional(sc, "Bairro (vazio para null): "));
        u.setEnderecoCEP(lerTextoOpcional(sc, "CEP (vazio para null): "));
        u.setEnderecoCidade(lerTextoOpcional(sc, "Cidade (vazio para null): "));
        u.setEnderecoEstado(lerTextoOpcional(sc, "Estado (vazio para null): "));

        Usuario salvo = usuarioService.salvar(u);
        System.out.println("Usuário cadastrado com sucesso: " + salvo);
    }

    private void listarUsuarios() {
        List<Usuario> lista = usuarioService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void buscarUsuarioPorId(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do usuário: ");
        Usuario usuario = usuarioService.buscarPorId(id);
        System.out.println(usuario);
    }

    private void atualizarUsuario(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do usuário a atualizar: ");

        Usuario u = new Usuario();
        u.setNome(lerTexto(sc, "Novo nome: "));
        u.setTelefone(lerTexto(sc, "Novo telefone: "));
        u.setEmail(lerTexto(sc, "Novo email: "));
        u.setEnderecoRua(lerTexto(sc, "Nova rua: "));
        u.setEnderecoNumero(lerInteiroOpcional(sc, "Novo número (vazio para null): "));
        u.setEnderecoComplemento(lerTextoOpcional(sc, "Novo complemento (vazio para null): "));
        u.setEnderecoBairro(lerTextoOpcional(sc, "Novo bairro (vazio para null): "));
        u.setEnderecoCEP(lerTextoOpcional(sc, "Novo CEP (vazio para null): "));
        u.setEnderecoCidade(lerTextoOpcional(sc, "Nova cidade (vazio para null): "));
        u.setEnderecoEstado(lerTextoOpcional(sc, "Novo estado (vazio para null): "));

        Usuario atualizado = usuarioService.atualizar(id, u);
        System.out.println("Usuário atualizado: " + atualizado);
    }

    private void removerUsuario(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do usuário a remover: ");
        usuarioService.deletar(id);
        System.out.println("Usuário removido com sucesso.");
    }

    private void menuMedicamento(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- CRUD MEDICAMENTO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Remover");
            System.out.println("0 - Voltar");

            opcao = lerInteiro(sc, "Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarMedicamento(sc);
                case 2 -> listarMedicamentos();
                case 3 -> buscarMedicamentoPorId(sc);
                case 4 -> atualizarMedicamento(sc);
                case 5 -> removerMedicamento(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarMedicamento(Scanner sc) {
        Medicamento m = new Medicamento();

        System.out.println("\nCadastro de Medicamento");
        m.setNomeComercial(lerTexto(sc, "Nome comercial: "));
        m.setNomeGenerico(lerTextoOpcional(sc, "Nome genérico (vazio para null): "));
        m.setQuantidade(lerQuantidadeTipo(sc));
        m.setFormaUso(lerTextoOpcional(sc, "Forma de uso (vazio para null): "));
        m.setObservacao(lerTextoOpcional(sc, "Observação (vazio para null): "));

        Medicamento salvo = medicamentoService.salvar(m);
        System.out.println("Medicamento cadastrado com sucesso: " + salvo);
    }

    private void listarMedicamentos() {
        List<Medicamento> lista = medicamentoService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum Medicamento encontrado.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void buscarMedicamentoPorId(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do Medicamento: ");
        Medicamento Medicamento = medicamentoService.buscarPorId(id);
        System.out.println(Medicamento);
    }

    private void atualizarMedicamento(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do Medicamento a atualizar: ");

        Medicamento m = new Medicamento();
        m.setNomeComercial(lerTexto(sc, "Novo nome comercial: "));
        m.setNomeGenerico(lerTextoOpcional(sc, "Novo nome genérico (vazio para null): "));
        m.setQuantidade(lerQuantidadeTipo(sc));
        m.setFormaUso(lerTextoOpcional(sc, "Nova forma de uso (vazio para null): "));
        m.setObservacao(lerTextoOpcional(sc, "Nova observação (vazio para null): "));

        Medicamento atualizado = medicamentoService.atualizar(id, m);
        System.out.println("Medicamento atualizado: " + atualizado);
    }

    private void removerMedicamento(Scanner sc) {
        Integer id = lerInteiro(sc, "ID do Medicamento a remover: ");
        medicamentoService.deletar(id);
        System.out.println("Medicamento removido com sucesso.");
    }


    private Integer lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = sc.nextLine();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private Integer lerInteiroOpcional(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String valor = sc.nextLine().trim();
        if (valor.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Número inválido.");
        }
    }

    private String lerTexto(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String valor = sc.nextLine();
        if (valor == null || valor.trim().isEmpty()) {
            throw new RuntimeException("Campo obrigatório.");
        }
        return valor;
    }

    private String lerTextoOpcional(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String valor = sc.nextLine().trim();
        return valor.isEmpty() ? null : valor;
    }

    private QuantidadeTipo lerQuantidadeTipo(Scanner sc) {
        while (true) {
            System.out.print("Quantidade [UNIDADE/ML]: ");
            String valor = sc.nextLine().trim().toUpperCase();
            try {
                return QuantidadeTipo.valueOf(valor);
            } catch (Exception e) {
                System.out.println("Valor inválido. Use: UNIDADE ou ML.");
            }
        }
    }

    private StatusAlerta lerStatusAlerta(Scanner sc) {
        while (true) {
            System.out.print("Status alerta [EMITIDO/PENDENTE/CONFIRMADO]: ");
            String valor = sc.nextLine().trim().toUpperCase();
            try {
                return StatusAlerta.valueOf(valor);
            } catch (Exception e) {
                System.out.println("Valor inválido. Use: EMITIDO, PENDENTE ou CONFIRMADO.");
            }
        }
    }

    private ConfirmacaoConsumo lerConfirmacaoConsumo(Scanner sc) {
        while (true) {
            System.out.print("Confirmação consumo [SIM/NAO]: ");
            String valor = sc.nextLine().trim().toUpperCase();
            try {
                return ConfirmacaoConsumo.valueOf(valor);
            } catch (Exception e) {
                System.out.println("Valor inválido. Use: SIM ou NAO.");
            }
        }
    }
}
