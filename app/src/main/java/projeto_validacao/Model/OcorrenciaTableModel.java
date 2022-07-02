/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_validacao.Model;

import DAO.CidadaoDAO;
import DAO.OcorrenciaDAO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
public class OcorrenciaTableModel extends AbstractTableModel {

    private List<OcorrenciaModel> list = new ArrayList();
    private final String[] columnNames = {"ID", "LOCAL", "DATA", "DESCRIÇÃO"};

    public OcorrenciaTableModel(List<OcorrenciaModel> list) {
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OcorrenciaModel ocorrencia = new OcorrenciaModel();
        switch (columnIndex) {
            case 0:
                ocorrencia = list.get(rowIndex);
                return ocorrencia.getId();
            case 1:
                ocorrencia = list.get(rowIndex);

                return ocorrencia.getLocal();
            case 2:
                ocorrencia = list.get(rowIndex);
                if (ocorrencia.getData() != null) {
                    return ocorrencia.getData().toLocaleString();
                }
                return ocorrencia.getData();
            case 3:
                ocorrencia = list.get(rowIndex);

                return ocorrencia.getDescricao();
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

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        OcorrenciaModel ocorrencia = new OcorrenciaModel();

        switch (columnIndex) {
            case 0: {
                ocorrencia = list.get(rowIndex);
                list.get(rowIndex).setId(Integer.parseInt(aValue.toString()));
                break;
            }
            case 1: {
                ocorrencia = list.get(rowIndex);
                list.get(rowIndex).setLocal(aValue.toString());
                ocorrencia.setId(list.get(rowIndex).getId());
                ocorrencia.setLocal(aValue.toString());
                if (ocorrencia.getId() > 0) {
                    OcorrenciaDAO.updateOcorrencia(ocorrencia);
                }

                break;
            }
            case 2: {
                ocorrencia = list.get(rowIndex);

                try {
                    list.get(rowIndex).setData(new SimpleDateFormat("dd/MM/yyyy").parse(aValue.toString()));
                    ocorrencia.setId(list.get(rowIndex).getId());
                    ocorrencia.setData(new SimpleDateFormat("dd/MM/yyyy").parse(aValue.toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(OcorrenciaTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ocorrencia.getId() > 0) {
                    OcorrenciaDAO.updateOcorrencia(ocorrencia);
                }

                break;
            }
            case 3: {
                ocorrencia = list.get(rowIndex);
                list.get(rowIndex).setDescricao(aValue.toString());
                ocorrencia.setId(list.get(rowIndex).getId());
                ocorrencia.setDescricao(aValue.toString());
                if (ocorrencia.getId() > 0) {
                    OcorrenciaDAO.updateOcorrencia(ocorrencia);
                }

                break;
            }

            default:
                System.out.println("Coluna não encontrada ou não editavel!");
                break;
        }
    }

}
