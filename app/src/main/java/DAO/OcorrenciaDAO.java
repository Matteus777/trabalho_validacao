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
import projeto_validacao.Model.OcorrenciaModel;
import projeto_validacao.Persistence;

/**
 *
 * @author Matteus
 */
public class OcorrenciaDAO {
  public static void insertOcorrencia(OcorrenciaModel ocorrencia) {
        String sql = "INSERT INTO ocorrencia(local,data,descricao,cidadaoId) VALUES(?,?,?,?);";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, ocorrencia.getLocal());
                pstmt.setDate(2, new java.sql.Date(ocorrencia.getData().getTime()));
                pstmt.setString(3, ocorrencia.getDescricao());
                pstmt.setInt(4,ocorrencia.getCidadaoId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<OcorrenciaModel> getOcorrencias(int cidadaoId) {
        List<OcorrenciaModel> items = new ArrayList();
        String sql = "SELECT * FROM ocorrencia where cidadaoId = " + cidadaoId;
        try ( Connection conn = Persistence.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OcorrenciaModel item = new OcorrenciaModel();
                item.setId(rs.getInt("id"));
                item.setData(rs.getDate("data"));
                item.setDescricao(rs.getString("descricao"));
                item.setLocal(rs.getString("local"));
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public static void updateOcorrencia(OcorrenciaModel ocorrencia) {
        String sql = "UPDATE cidadao set local=?,data=?,descricao=? where id = ?;";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, ocorrencia.getLocal());
                pstmt.setDate(2, new java.sql.Date(ocorrencia.getData().getTime()));
                pstmt.setString(3, ocorrencia.getDescricao());
                pstmt.setInt(4, ocorrencia.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteByCidadao(int cidadaoId) {
        String sql = "DELETE FROM ocorrencia where cidadaoId = ?";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cidadaoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void delete(int id) {
        String sql = "DELETE FROM ocorrencia where id = ?";
        try ( Connection conn = Persistence.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
