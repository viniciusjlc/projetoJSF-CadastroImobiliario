package br.com.DAO;

import br.com.model.Usuario;
import br.com.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Boolean autenticar(Usuario usuario) {
        String sql = "select id " +
                "from imobiliario.usuarios u " +
                "where u.email ilike ? " +
                "and u.senha = ?";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ResultSet rs = ps.executeQuery();
            return rs.next();
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

    public Boolean cadastrar(Usuario usuario) {
        String sql = "INSERT INTO imobiliario.usuarios " +
                "(email, nome, senha)  " +
                "VALUES(?, ?, ?)";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.execute();
            conexao.commit();
            return true;
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

    public Usuario consultarPorEmail(Usuario usuario) {
        String sql = "select id, email, senha, nome " +
                "from imobiliario.usuarios u " +
                "where u.email = ? ";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ResultSet rs = ps.executeQuery();
            Usuario u = new Usuario();
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
            }
            return u;
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
