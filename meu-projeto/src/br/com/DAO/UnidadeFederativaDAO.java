package br.com.DAO;

import br.com.model.Builder.UnidadeFederativaBuilder;
import br.com.model.UnidadeFederativa;
import br.com.util.ConnectionFactory;
import br.gov.al.maceio.framework.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.shared.queries.QueriesUnidadeFederativa.QUERY_CONSULTA_LISTAR_UNIDADE_FEDERATIVA;
import static br.gov.al.maceio.framework.shared.persistence.StatementFactory.criarPreparedStatement;

public class UnidadeFederativaDAO {

    public List<UnidadeFederativa> listar() {
        List<UnidadeFederativa> listaUnidadeFederativa = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = criarPreparedStatement(connection, QUERY_CONSULTA_LISTAR_UNIDADE_FEDERATIVA)
                     .construir();
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next())
                listaUnidadeFederativa.add(new UnidadeFederativaBuilder().mapear(resultSet));
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return listaUnidadeFederativa;
    }

}
