package br.com.model.Builder;

import br.com.model.UnidadeFederativa;
import br.gov.al.maceio.framework.shared.Builder;

import java.sql.ResultSet;

import static br.gov.al.maceio.framework.util.BancoUtil.recuperarInteiro;
import static br.gov.al.maceio.framework.util.BancoUtil.recuperarString;

public class UnidadeFederativaBuilder {
    private UnidadeFederativa unidadeFederativa;

    public UnidadeFederativaBuilder() {
        this.unidadeFederativa = new UnidadeFederativa();
    }

    private UnidadeFederativaBuilder comId(Integer id) {
        this.unidadeFederativa.setId(id);
        return this;
    }

    private UnidadeFederativaBuilder comNome(String nome) {
        this.unidadeFederativa.setNome(nome);
        return this;
    }

    private UnidadeFederativaBuilder comSigla(String sigla) {
        this.unidadeFederativa.setSigla(sigla);
        return this;
    }

    public UnidadeFederativa construir() {
        return this.unidadeFederativa;
    }

    public UnidadeFederativa mapear(ResultSet rs) {
        return this
                .comId(recuperarInteiro(rs, "id"))
                .comNome(recuperarString(rs, "nome"))
                .comSigla(recuperarString(rs, "sigla"))
                .construir();
    }
}
