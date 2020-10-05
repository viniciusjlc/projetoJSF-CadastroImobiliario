package br.com.shared.forms;

import br.com.model.Usuario;

import java.util.Objects;

public class AutenticacaoForm {

    private String email;

    private String senha;

    public AutenticacaoForm() {
    }

    public AutenticacaoForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public AutenticacaoForm fromUsuario(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AutenticacaoForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public AutenticacaoForm setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutenticacaoForm that = (AutenticacaoForm) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, senha);
    }

    @Override
    public String toString() {
        return "AutenticacaoForm{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
