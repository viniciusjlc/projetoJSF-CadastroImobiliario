package br.com.model;

import java.io.Serializable;

public class CadastroImobiliario implements Serializable {

    private Integer id;
    private String cep;
    private String endereco;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private TipoLogradouro tipoLogradouro;
    private UnidadeFederativa unidadeFederativa;
    private Usuario usuario;

    public CadastroImobiliario() {
    }

    public CadastroImobiliario(String cep, String endereco, String complemento, String numero, String bairro, String cidade) {
        this.cep = cep;
        this.endereco = endereco;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.tipoLogradouro = new TipoLogradouro();
        this.unidadeFederativa = new UnidadeFederativa();
        this.usuario = new Usuario();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
