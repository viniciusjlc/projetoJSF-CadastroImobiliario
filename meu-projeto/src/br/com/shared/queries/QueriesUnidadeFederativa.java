package br.com.shared.queries;

public class QueriesUnidadeFederativa {
    public static final String QUERY_CONSULTA_LISTAR_UNIDADE_FEDERATIVA =
            "select id, nome, sigla " +
                    "from imobiliario.unidade_federativa uf " +
                    "order by nome";

}
