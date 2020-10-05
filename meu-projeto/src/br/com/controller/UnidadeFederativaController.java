package br.com.controller;

import br.com.model.UnidadeFederativa;
import br.com.service.UnidadeFederativaService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "unidadeFederativaMB")
public class UnidadeFederativaController {
    private List<UnidadeFederativa> listaUnidadeFederativas;

    public UnidadeFederativaController() {
        this.listaUnidadeFederativas = new UnidadeFederativaService().listar();
    }

    public List<UnidadeFederativa> getListaUnidadeFederativas() {
        return listaUnidadeFederativas;
    }

    public void setListaUnidadeFederativas(List<UnidadeFederativa> listaUnidadeFederativas) {
        this.listaUnidadeFederativas = listaUnidadeFederativas;
    }
}
