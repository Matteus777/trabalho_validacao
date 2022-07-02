/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projeto_validacao.Model.CidadaoModel;
import projeto_validacao.Persistence;

/**
 *
 * @author Matteus
 */
public class CidadaoDAO {

    public static List<CidadaoModel> getCidadao() {
        List<CidadaoModel> items = new ArrayList();
        String sql = "SELECT * FROM cidadao order by nome;";
        try ( Connection conn = Persistence.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CidadaoModel item = new CidadaoModel();
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setDocumento(rs.getString("documento"));
                item.setEndereco(rs.getString("endereco"));
                item.setTelefone(rs.getString("telefone"));
                item.setEmail(rs.getString("email"));
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public static void insertCidadao(CidadaoModel cidadao) {
        String sql = "INSERT INTO cidadao(nome,documento,endereco,telefone,email) VALUES(?,?,?,?,?);";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, cidadao.getNome());
                pstmt.setString(2, cidadao.getDocumento());
                pstmt.setString(3, cidadao.getEndereco());
                pstmt.setString(4, cidadao.getTelefone());
                pstmt.setString(5, cidadao.getEmail());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static  void updateCidadao(CidadaoModel cidadao) {
        String sql = "UPDATE cidadao set nome=?,documento=?,endereco=?,telefone=?, email=? where id = ?;";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, cidadao.getNome());
                pstmt.setString(2, cidadao.getDocumento());
                pstmt.setString(3, cidadao.getEndereco());
                pstmt.setString(4, cidadao.getTelefone());
                pstmt.setString(5, cidadao.getEmail());
                pstmt.setInt(6, cidadao.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCidadao(int id) {
        OcorrenciaDAO.deleteByCidadao(id);
        String sql = "DELETE FROM cidadao where id = ?";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
