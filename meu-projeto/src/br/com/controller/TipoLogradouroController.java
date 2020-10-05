package br.com.controller;

import br.com.model.TipoLogradouro;
import br.com.service.TipoLogradouroService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean(name = "tipoLogradouroMB")
public class TipoLogradouroController {
    private List<TipoLogradouro> listaTipoLogradouro;

    public TipoLogradouroController() {
        this.listaTipoLogradouro = new TipoLogradouroService().listar();
    }

    public List<TipoLogradouro> getListaTipoLogradouro() {
        return listaTipoLogradouro;
    }

    public void setListaTipoLogradouro(List<TipoLogradouro> listaTipoLogradouro) {
        this.listaTipoLogradouro = listaTipoLogradouro;
    }
}
