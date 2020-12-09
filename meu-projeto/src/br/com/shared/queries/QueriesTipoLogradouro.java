package br.com.shared.queries;

public class QueriesTipoLogradouro {
    public static final String QUERY_CONSULTA_LISTAR_TIPO_LOGRADOURO =
            "select id, descricao " +
                    "from imobiliario.tipo_logradouro " +
                    "order by descricao";

}
