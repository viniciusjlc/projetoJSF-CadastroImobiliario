package br.com.service;

import br.com.model.Usuario;
import br.com.shared.dto.TokenDTO;
import br.com.shared.forms.AutenticacaoForm;
import br.com.shared.forms.CadastroUsuarioForm;
import br.com.util.JWTUtil;
import br.com.util.SessionUtil;

import static br.com.shared.constantes.Constantes.urlApi.usuario.*;
import static br.com.util.VerificadorUtil.naoEstaNuloOuVazio;

public class UsuarioService {

    public Boolean autenticar(Usuario usuario) {
        try {
            TokenDTO tokenDTO = JWTUtil.getInstance().chamarMetodoPost(urlAutenticar, new AutenticacaoForm().fromUsuario(usuario), TokenDTO.class);
            JWTUtil.getInstance().gravarToken(tokenDTO);
            SessionUtil.getInstance().gravarUsuario(this.consultarPorEmail(usuario));
            return (naoEstaNuloOuVazio(tokenDTO.getToken()));
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public Boolean cadastrar(Usuario usuario) {
        try {
            JWTUtil.getInstance().chamarMetodoPost(urlCadastrarUsuario, new CadastroUsuarioForm().fromUsuario(usuario), Usuario.class);
            return this.autenticar(usuario);
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public Usuario consultarPorEmail(Usuario usuario) {
        try {
            return JWTUtil.getInstance().chamarMetodoPost(urlConsultarPorEmail, new AutenticacaoForm().fromUsuario(usuario), Usuario.class);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
}
