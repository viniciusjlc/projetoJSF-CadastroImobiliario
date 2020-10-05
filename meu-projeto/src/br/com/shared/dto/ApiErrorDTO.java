package br.com.shared.dto;

import java.io.Serializable;

public class ApiErrorDTO implements Serializable {
    private String campo;
    private String mensagem;
    private String stacktrace;

    public ApiErrorDTO() {
    }

    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStacktrace() {
        return this.stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
