package br.com.model.Builder;

import br.com.model.TipoLogradouro;
import br.com.model.TipoLogradouro;

import java.io.Serializable;
import java.sql.ResultSet;

import static br.gov.al.maceio.framework.util.BancoUtil.recuperarInteiro;
import static br.gov.al.maceio.framework.util.BancoUtil.recuperarString;

public class TipoLogradouroBuilder implements Serializable {
    private TipoLogradouro tipoLogradouro;

    public TipoLogradouroBuilder() {
        this.tipoLogradouro = new TipoLogradouro();
    }

    private TipoLogradouroBuilder comId(Integer id) {
        this.tipoLogradouro.setId(id);
        return this;
    }

    private TipoLogradouroBuilder comDescricao(String descricao) {
        this.tipoLogradouro.setDescricao(descricao);
        return this;
    }


    public TipoLogradouro construir() {
        return this.tipoLogradouro;
    }

    public TipoLogradouro mapear(ResultSet rs) {
        return this
                .comId(recuperarInteiro(rs, "id"))
                .comDescricao(recuperarString(rs, "descricao"))
                .construir();
    }

}
