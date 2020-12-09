package br.com.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static final String RESOURCE_SIIMM = "resource/teste";

    private static final String DRIVER_BANCO = "org.postgresql.Driver";
    private static final String URL_BANCO = properties(RESOURCE_SIIMM).getProperty("url");
    private static final String USUARIO_BANCO = properties(RESOURCE_SIIMM).getProperty("user");
    private static final String SENHA_BANCO = properties(RESOURCE_SIIMM).getProperty("pass");

    public static Connection getConnection() {
        return getConnection(false);
    }

    public static Connection getConnection(boolean autoCommit) {
        try {
            Class.forName(DRIVER_BANCO);
            Connection conexao = DriverManager.getConnection(URL_BANCO, USUARIO_BANCO, SENHA_BANCO);
            conexao.setAutoCommit(autoCommit);
            return conexao;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void fecharConexao(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Properties properties(String nomeJndi) {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            Properties bean = (Properties) envCtx.lookup(nomeJndi);
            return bean;
        } catch (NamingException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
