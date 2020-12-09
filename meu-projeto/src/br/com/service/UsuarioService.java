package br.com.service;

import br.com.DAO.UsuarioDAO;
import br.com.model.Usuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Boolean autenticar(Usuario usuario) {
        return this.usuarioDAO.autenticar(usuario);
    }

    public Boolean cadastrar(Usuario usuario) {
        return this.usuarioDAO.cadastrar(usuario);
    }

    public Usuario consultarPorEmail(Usuario usuario) {
        return this.usuarioDAO.consultarPorEmail(usuario);

    }
}
