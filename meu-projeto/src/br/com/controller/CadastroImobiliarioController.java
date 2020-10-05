package br.com.controller;

import br.com.model.CadastroImobiliario;
import br.com.model.TipoLogradouro;
import br.com.model.UnidadeFederativa;
import br.com.service.CadastroImobiliarioService;
import br.com.service.TipoLogradouroService;
import br.com.service.UnidadeFederativaService;
import br.com.util.SessionUtil;
import net.bootsfaces.component.message.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static br.com.util.JSFUtil.fecharDialog;

@ViewScoped
@ManagedBean(name = "cadastroImobiliarioMB")
public class CadastroImobiliarioController implements Serializable {
    private CadastroImobiliarioService cadastroImobiliarioService;

    private Integer idUsuarioAtual;
    private List<CadastroImobiliario> listaCadastrosImobiliario;
    private List<CadastroImobiliario> listaCadastrosImobiliarioSelecionados;
    private Boolean renderizarCadastrar;
    private String mensagemDeErroCadastrar = null;
    private String mensagemDeErroEditar = null;
    /*private  UnidadeFederativa unidadeFederativaSelecionada;
    private TipoLogradouro tipoLogradouroSelecionada;*/
    private CadastroImobiliario cadastroImobiliario;
    private List<UnidadeFederativa> listaUnidadeFederativas;
    private List<TipoLogradouro> listaTipoLogradouro;
    private String tituloDlgCadastrarEditar;
    private List<Message> mensagens;

    public CadastroImobiliarioController() {
        this.cadastroImobiliarioService = new CadastroImobiliarioService();
        listarCadatrosPorUsuarioAtual();
        iniciarDadosParaCadastro();
        this.listaUnidadeFederativas = new UnidadeFederativaService().listar();
        this.listaTipoLogradouro = new TipoLogradouroService().listar();
        this.listaCadastrosImobiliarioSelecionados = new ArrayList<>();
        this.mensagens = new ArrayList<>();
    }

    public void salvarCadastroImobiliario() {
        if (!verificarSeValoresDoCadastroPreenchidos()) {
            this.cadastroImobiliario.getUsuario().setId(SessionUtil.getInstance().getUserSession().getId());
            this.cadastroImobiliarioService.cadastrar(this.cadastroImobiliario);
            this.listarCadatrosPorUsuarioAtual();
            fecharDialog("dlgCadastroImobiliario");
        } else {
            this.mensagemDeErroCadastrar = "Todos os valores devem ser preenchidos!";
        }
    }

    public void deletarCadastroImobiliario(CadastroImobiliario cadastroImobiliario) {
        this.cadastroImobiliarioService.excluir(cadastroImobiliario.getId());
        this.listarCadatrosPorUsuarioAtual();
    }


    public void abrirEditarCadastroImobiliario(CadastroImobiliario cadastroImobiliario) {
        this.cadastroImobiliario = cadastroImobiliario;
    }

    private boolean verificarSeValoresDoCadastroPreenchidos() {
        return this.cadastroImobiliario.getCep().equals("") &&
                this.cadastroImobiliario.getEndereco().equals("") &&
                this.cadastroImobiliario.getComplemento().equals("") &&
                this.cadastroImobiliario.getNumero().equals("") &&
                this.cadastroImobiliario.getBairro().equals("") &&
                this.cadastroImobiliario.getCidade().equals("") &&
                this.cadastroImobiliario.getUnidadeFederativa().getId() == null &&
                this.cadastroImobiliario.getTipoLogradouro().getId() == null;
    }

    private void iniciarDadosParaCadastro() {
        this.cadastroImobiliario = new CadastroImobiliario("", "", "", "", "", "");
    }

    private void listarCadatrosPorUsuarioAtual() {
        this.listaCadastrosImobiliario = cadastroImobiliarioService.consultarImobiliarioPorUsuario(SessionUtil.getInstance().getUserSession().getId());
    }

    public List<CadastroImobiliario> getListaCadastrosImobiliario() {
        return listaCadastrosImobiliario;
    }

    public void setListaCadastrosImobiliario(List<CadastroImobiliario> listaCadastrosImobiliario) {
        this.listaCadastrosImobiliario = listaCadastrosImobiliario;
    }

    public List<CadastroImobiliario> getListaCadastrosImobiliarioSelecionados() {
        return listaCadastrosImobiliarioSelecionados;
    }

    public void setListaCadastrosImobiliarioSelecionados(List<CadastroImobiliario> listaCadastrosImobiliarioSelecionados) {
        this.listaCadastrosImobiliarioSelecionados = listaCadastrosImobiliarioSelecionados;
    }

    public Boolean getRenderizarCadastrar() {
        return renderizarCadastrar;
    }

    public void setRenderizarCadastrar(Boolean renderizarCadastrar) {
        this.renderizarCadastrar = renderizarCadastrar;
    }

    public String getMensagemDeErroCadastrar() {
        return mensagemDeErroCadastrar;
    }

    public void setMensagemDeErroCadastrar(String mensagemDeErroCadastrar) {
        this.mensagemDeErroCadastrar = mensagemDeErroCadastrar;
    }

    public String getMensagemDeErroEditar() {
        return mensagemDeErroEditar;
    }

    public void setMensagemDeErroEditar(String mensagemDeErroEditar) {
        this.mensagemDeErroEditar = mensagemDeErroEditar;
    }

    public CadastroImobiliario getCadastroImobiliario() {
        return cadastroImobiliario;
    }

    public void setCadastroImobiliario(CadastroImobiliario cadastroImobiliario) {
        this.cadastroImobiliario = cadastroImobiliario;
    }

    public List<UnidadeFederativa> getListaUnidadeFederativas() {
        return listaUnidadeFederativas;
    }

    public void setListaUnidadeFederativas(List<UnidadeFederativa> listaUnidadeFederativas) {
        this.listaUnidadeFederativas = listaUnidadeFederativas;
    }

    public List<TipoLogradouro> getListaTipoLogradouro() {
        return listaTipoLogradouro;
    }

    public void setListaTipoLogradouro(List<TipoLogradouro> listaTipoLogradouro) {
        this.listaTipoLogradouro = listaTipoLogradouro;
    }

    public String getTituloDlgCadastrarEditar() {
        return tituloDlgCadastrarEditar;
    }

    public void setTituloDlgCadastrarEditar(String tituloDlgCadastrarEditar) {
        this.tituloDlgCadastrarEditar = tituloDlgCadastrarEditar;
    }

    public List<Message> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Message> mensagens) {
        this.mensagens = mensagens;
    }
}
