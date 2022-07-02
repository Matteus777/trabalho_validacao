package projeto_validacao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matteus
 */
public class Persistence {

    static Dotenv dotenv = Dotenv.load();
    static String url = "jdbc:mysql://" + dotenv.get("HOST") + ":3306/" + dotenv.get("DATABASE");
    static String username = dotenv.get("USER");
    static String password = dotenv.get("PASSWORD");

    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;

    }

    public static boolean createDB() {
        createCidadaoTable();
        createOcorrenciaTable();
       
        System.out.println("Banco Criado.");
        return true;
    }

    public static boolean createCidadaoTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cidadao (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "nome VARCHAR(80),"
                + "documento VARCHAR(11),"
                + "endereco VARCHAR(80) NOT NULL,"
                + "telefone VARCHAR(11) NOT NULL,"
                + "email VARCHAR(30));";

        try ( Connection conn = getConnection();  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Cidadao Criada!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean createOcorrenciaTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ocorrencia (id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                + "local VARCHAR(60) NOT NULL,"
                + "data DATE NOT NULL,"
                + "descricao VARCHAR(60) NOT NULL,"
                + "cidadaoId INT NOT NULL,"
                + "FOREIGN KEY (cidadaoId) REFERENCES cidadao(id));";

        try ( Connection conn = getConnection();  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Ocorrencia Criada!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

   

}
