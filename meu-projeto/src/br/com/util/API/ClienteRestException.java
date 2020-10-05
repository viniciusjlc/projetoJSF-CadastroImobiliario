package br.com.util.API;

public class ClienteRestException extends RuntimeException {
    public ClienteRestException(String mensagem) {
        super(mensagem);
    }
}
