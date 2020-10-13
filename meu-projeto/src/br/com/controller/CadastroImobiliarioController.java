package br.com.controller;

import br.com.model.CadastroImobiliario;
import br.com.model.TipoLogradouro;
import br.com.model.UnidadeFederativa;
import br.com.service.CadastroImobiliarioService;
import br.com.service.TipoLogradouroService;
import br.com.service.UnidadeFederativaService;
import br.com.util.SessionUtil;
import net.bootsfaces.component.message.Message;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static br.com.util.JSFUtil.fecharDialog;
import static br.com.util.StringUtil.putMascara;
import static br.com.util.StringUtil.retirarMascara;
import static br.com.util.VerificadorUtil.estaNulo;

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
            this.cadastroImobiliario.setCep(retirarMascara(this.cadastroImobiliario.getCep()));
            this.cadastroImobiliario.getUsuario().setId(SessionUtil.getInstance().getUserSession().getId());
            this.cadastroImobiliarioService.cadastrar(this.cadastroImobiliario);
            this.listarCadatrosPorUsuarioAtual();
            fecharDialog("dlgCadastroImobiliario");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado", "Cadastro imobiliario salvo com sucesso."));
        } else {
            this.mensagemDeErroCadastrar = "Todos os valores devem ser preenchidos!";
        }
    }

    public void deletarCadastroImobiliario(CadastroImobiliario cadastroImobiliario) {
        this.cadastroImobiliarioService.excluir(cadastroImobiliario.getId());
        this.listarCadatrosPorUsuarioAtual();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado", "Cadastro excluido com sucesso."));
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

    public String retornarCEP(String cep) {
        return putMascara(cep, "#####-###");
    }

    private void listarCadatrosPorUsuarioAtual() {
        this.listaCadastrosImobiliario = cadastroImobiliarioService.consultarImobiliarioPorUsuario(estaNulo(SessionUtil.getInstance().getUserSession().getId()) ? 1 : SessionUtil.getInstance().getUserSession().getId());
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        CadastroImobiliario ci = (CadastroImobiliario) value;
        return ci.getCep().toLowerCase().contains(filterText)
                || ci.getEndereco().toLowerCase().contains(filterText)
                || ci.getUnidadeFederativa().getNome().toLowerCase().contains(filterText)
                || ci.getCidade().toLowerCase().contains(filterText)
                || ci.getTipoLogradouro().getDescricao().toLowerCase().contains(filterText);
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
