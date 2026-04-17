package br.uninter.medalerta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cuidador")
public class Cuidador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCuidador")
    private Integer idCuidador;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String enderecoRua;

    @Column(nullable = false)
    private Integer enderecoNumero;

    @Column(length = 50)
    private String enderecoComplemento;

    @Column(nullable = false, length = 50)
    private String enderecoBairro;

    @Column(name = "enderecoCep", nullable = false, length = 10)
    private String enderecoCEP;

    @Column(nullable = false, length = 50)
    private String enderecoCidade;

    @Column(nullable = false, length = 2)
    private String enderecoEstado;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false, unique = true)
    private Usuario usuario;

    public Cuidador(){

    }

    public Integer getIdCuidador(){
        return idCuidador;
    }
    
    public void setIdCuidador(Integer idCuidador){
        this.idCuidador = idCuidador;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    public Integer getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(Integer enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }

    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getEnderecoCEP() {
        return enderecoCEP;
    }

    public void setEnderecoCEP(String enderecoCEP) {
        this.enderecoCEP = enderecoCEP;
    }

    public String getEnderecoCidade() {
        return enderecoCidade;
    }

    public void setEnderecoCidade(String enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }

    public String getEnderecoEstado() {
        return enderecoEstado;
    }

    public void setEnderecoEstado(String enderecoEstado) {
        this.enderecoEstado = enderecoEstado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cuidador{" +
                "idCuidador=" + idCuidador +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", enderecoRua='" + enderecoRua + '\'' +
                ", enderecoNumero=" + enderecoNumero +
                ", enderecoComplemento='" + enderecoComplemento + '\'' +
                ", enderecoBairro='" + enderecoBairro + '\'' +
                ", enderecoCEP='" + enderecoCEP + '\'' +
                ", enderecoCidade='" + enderecoCidade + '\'' +
                ", enderecoEstado='" + enderecoEstado + '\'' +
                ", idUsuario=" + (usuario != null ? usuario.getIdUsuario() : null) +
                '}';
    }
}   
