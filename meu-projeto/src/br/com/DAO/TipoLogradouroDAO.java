package br.com.DAO;

import br.com.model.TipoLogradouro;
import br.com.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.shared.queries.QueriesTipoLogradouro.QUERY_CONSULTA_LISTAR_TIPO_LOGRADOURO;

public class TipoLogradouroDAO {
    public List<TipoLogradouro> listar() {
        List<TipoLogradouro> listaTipoLogradouro = new ArrayList<>();
        String sql = QUERY_CONSULTA_LISTAR_TIPO_LOGRADOURO;
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TipoLogradouro tl = new TipoLogradouro();
                tl.setId(rs.getInt("id"));
                tl.setDescricao(rs.getString("descricao"));
                listaTipoLogradouro.add(tl);
            }
            return listaTipoLogradouro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /*public List<TipoLogradouro> listar() {
        List<TipoLogradouro> listaTipoLogradouro = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = criarPreparedStatement(connection, "")
                     .construir();
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next())
                listaTipoLogradouro.add(new TipoLogradouroBuilder().mapear(resultSet));
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return listaTipoLogradouro;
    }*/

}
