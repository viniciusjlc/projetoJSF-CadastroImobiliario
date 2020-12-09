package br.com.DAO;

import br.com.model.CadastroImobiliario;
import br.com.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroImobiliarioDAO {

    public List<CadastroImobiliario> consultarImobiliarioPorUsuario(Integer idUsuario) {
        List<CadastroImobiliario> listaCadastroImobiliario = new ArrayList<>();
        String sql = "select ci.id, " +
                "       ci.cep, " +
                "       ci.endereco, " +
                "       ci.complemento, " +
                "       ci.numero, " +
                "       ci.bairro, " +
                "       ci.cidade, " +
                "       tl.descricao as descricao_tipo_logradouro, " +
                "       uf.nome      as nome_unidade_federativa, " +
                "       uf.sigla      as sigla_unidade_federativa " +
                "from imobiliario.cadastro_imobiliario ci " +
                "         join imobiliario.tipo_logradouro tl on tl.id = ci.id_tipo_logradouro " +
                "         join imobiliario.unidade_federativa uf on uf.id = ci.id_unidade_federativa " +
                "where ci.id_usuario = ? " +
                "order by ci.id ";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CadastroImobiliario c = new CadastroImobiliario();
                c.setId(rs.getInt("id"));
                c.setCep(rs.getString("cep"));
                c.setEndereco(rs.getString("endereco"));
                c.setComplemento(rs.getString("complemento"));
                c.setNumero(rs.getString("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.getTipoLogradouro().setDescricao(rs.getString("descricao_tipo_logradouro"));
                c.getUnidadeFederativa().setNome(rs.getString("nome_unidade_federativa"));
                c.getUnidadeFederativa().setSigla(rs.getString("sigla_unidade_federativa"));
                listaCadastroImobiliario.add(c);
            }
            return listaCadastroImobiliario;
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

    public void cadastrar(CadastroImobiliario cadastroImobiliario) {
        String sql = "INSERT INTO imobiliario.cadastro_imobiliario " +
                "(cep, endereco, complemento, " +
                "id_tipo_logradouro, numero, bairro, " +
                "cidade, id_unidade_federativa, id_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "returning id";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cadastroImobiliario.getCep());
            ps.setString(2, cadastroImobiliario.getEndereco());
            ps.setString(3, cadastroImobiliario.getComplemento());
            ps.setInt(4, cadastroImobiliario.getTipoLogradouro().getId());
            ps.setString(5, cadastroImobiliario.getBairro());
            ps.setString(6, cadastroImobiliario.getBairro());
            ps.setString(7, cadastroImobiliario.getCidade());
            ps.setInt(8, cadastroImobiliario.getUnidadeFederativa().getId());
            ps.setInt(9, cadastroImobiliario.getUsuario().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cadastroImobiliario.setId(rs.getInt("id"));
            }
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

    public void alterar(CadastroImobiliario cadastroImobiliario) {
        String sql = "UPDATE imobiliario.cadastro_imobiliario " +
                "SET cep=?, " +
                "    endereco=?, " +
                "    complemento=?, " +
                "    id_tipo_logradouro=?, " +
                "    numero=?, " +
                "    bairro=?, " +
                "    cidade=?, " +
                "    id_unidade_federativa=?, " +
                "WHERE id = ?";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cadastroImobiliario.getCep());
            ps.setString(2, cadastroImobiliario.getEndereco());
            ps.setString(3, cadastroImobiliario.getComplemento());
            ps.setInt(4, cadastroImobiliario.getTipoLogradouro().getId());
            ps.setString(5, cadastroImobiliario.getBairro());
            ps.setString(6, cadastroImobiliario.getBairro());
            ps.setString(7, cadastroImobiliario.getCidade());
            ps.setInt(8, cadastroImobiliario.getUnidadeFederativa().getId());
            ps.setInt(9, cadastroImobiliario.getId());
            ps.executeUpdate();
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

    public void excluir(Integer idCadastroImobiliario) {
        String sql = "DELETE FROM imobiliario.cadastro_imobiliario\n" +
                " WHERE id = ? ";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idCadastroImobiliario);
            ps.execute();
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
}