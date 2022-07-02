/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_validacao.Model;

import DAO.CidadaoDAO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matteus
 */
public class CidadaoTableModel extends AbstractTableModel {

    private List<CidadaoModel> list = new ArrayList();
    private final String[] columnNames = {"ID", "NOME", "DOCUMENTO", "ENDEREÇO", "TELEFONE", "EMAIL"};

    public CidadaoTableModel(List<CidadaoModel> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CidadaoModel cidadao = new CidadaoModel();
        switch (columnIndex) {
            case 0:
                cidadao = list.get(rowIndex);
                return cidadao.getId();
            case 1:
                cidadao = list.get(rowIndex);

                return cidadao.getNome();
            case 2:
                cidadao = list.get(rowIndex);

                return cidadao.getDocumento();
            case 3:
                cidadao = list.get(rowIndex);

                return cidadao.getEndereco();
            case 4:
                cidadao = list.get(rowIndex);

                return cidadao.getTelefone();
            case 5:
                cidadao = list.get(rowIndex);

                return cidadao.getEmail();
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CidadaoModel cidadao = new CidadaoModel();
        switch (columnIndex) {
            case 0: {
                cidadao = list.get(rowIndex);
                list.get(rowIndex).setId(Integer.parseInt(aValue.toString()));
                break;
            }
            case 1: {
                cidadao = list.get(rowIndex);
                list.get(rowIndex).setNome(aValue.toString());
                cidadao.setId(list.get(rowIndex).getId());
                cidadao.setNome(aValue.toString());
                CidadaoDAO.updateCidadao(cidadao);
                break;
            }
            case 2: {
                cidadao = list.get(rowIndex);

                list.get(rowIndex).setDocumento(aValue.toString());
                cidadao.setId(list.get(rowIndex).getId());
                cidadao.setDocumento(aValue.toString());
                CidadaoDAO.updateCidadao(cidadao);

                break;
            }
            case 3: {
                cidadao = list.get(rowIndex);
                list.get(rowIndex).setEndereco(aValue.toString());
                cidadao.setId(list.get(rowIndex).getId());
                cidadao.setEndereco(aValue.toString());
                CidadaoDAO.updateCidadao(cidadao);

                break;
            }
            case 4: {
                cidadao = list.get(rowIndex);
                list.get(rowIndex).setTelefone(aValue.toString());
                cidadao.setId(list.get(rowIndex).getId());
                cidadao.setTelefone(aValue.toString());
                CidadaoDAO.updateCidadao(cidadao);

                break;
            }
            case 5: {
                cidadao = list.get(rowIndex);
                list.get(rowIndex).setEmail(aValue.toString());
                cidadao.setId(list.get(rowIndex).getId());
                cidadao.setEmail(aValue.toString());
                CidadaoDAO.updateCidadao(cidadao);
                break;
            }
            default:
                System.out.println("Coluna não encontrada ou não editavel!");
                break;
        }
    }

}
