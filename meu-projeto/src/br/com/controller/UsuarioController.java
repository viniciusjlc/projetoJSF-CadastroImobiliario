package br.com.controller;

import br.com.model.Usuario;
import br.com.service.UsuarioService;
import br.com.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

import static br.com.util.JSFUtil.fecharDialog;

@ViewScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioController implements Serializable {
    private UsuarioService usuarioService;

    private Usuario usuarioLogin;
    private Usuario usuarioCadastro;
    private String mensagemErroLogin;
    private String confirmacaoSenha;
    private String mensagemErroCadastro;
    private Boolean renderizarLogin;
    private Boolean renderizarCadastro;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
        this.usuarioLogin = new Usuario("teste@teste.com", "teste", "teste");
        this.logar();
        this.usuarioCadastro = new Usuario("", "", "");
        this.mensagemErroLogin = null;
        this.confirmacaoSenha = null;
        this.mensagemErroCadastro = null;
        this.renderizarLogin = false;
        this.renderizarCadastro = false;
    }

    public void limparMensagem() {
        this.mensagemErroLogin = null;
        this.mensagemErroCadastro = null;
    }

    public void destruirSessao() {
        SessionUtil.destruirSessao();
    }

    public void logar() {
        if (this.usuarioLogin.getEmail().equals("") || this.usuarioLogin.getSenha().equals("")) {
            this.mensagemErroLogin = "Email e Senha obrigatórios!";
        } else {
            Boolean logado = this.usuarioService.autenticar(usuarioLogin);
            if (logado) {
                fecharDialog("dlgLogin");
            } else {
                this.mensagemErroLogin = "Email ou Senha incorretos!";
            }
        }
    }

    public void cadastrar() {
        if (this.usuarioCadastro.getEmail().equals("") || this.usuarioCadastro.getSenha().equals("")
                || this.usuarioCadastro.getNome().equals("") || this.confirmacaoSenha.equals("")) {
            this.mensagemErroCadastro = "Todos os campos são obrigatórios!";
        } else if (!this.usuarioCadastro.getSenha().equals(this.confirmacaoSenha)) {
            this.mensagemErroCadastro = "Senha e confimação devem ser iguais!";
        } else {
            Boolean retorno = this.usuarioService.cadastrar(usuarioCadastro);
            if (retorno) {
                fecharDialog("dlgLogin");
                fecharDialog("dlgCadastrarUsuario");
            } else {
                this.mensagemErroCadastro = "Erro ao realizar o cadastro! Entre em contato com o administrador do sistema.";
            }
        }
    }

    public Boolean getLogado() {
        return SessionUtil.getInstance().getLogado();
    }

    public String getNomeUsuarioLogado() {
        return SessionUtil.getInstance().getUserSession().getNome();
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String getMensagemErroLogin() {
        return mensagemErroLogin;
    }

    public void setMensagemErroLogin(String mensagemErroLogin) {
        this.mensagemErroLogin = mensagemErroLogin;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getMensagemErroCadastro() {
        return mensagemErroCadastro;
    }

    public void setMensagemErroCadastro(String mensagemErroCadastro) {
        this.mensagemErroCadastro = mensagemErroCadastro;
    }

    public Boolean getRenderizarLogin() {
        return renderizarLogin;
    }

    public void setRenderizarLogin(Boolean renderizarLogin) {
        this.renderizarLogin = renderizarLogin;
    }

    public Boolean getRenderizarCadastro() {
        return renderizarCadastro;
    }

    public void setRenderizarCadastro(Boolean renderizarCadastro) {
        this.renderizarCadastro = renderizarCadastro;
    }
}
