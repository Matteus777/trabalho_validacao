/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projeto_validacao;

import DAO.CidadaoDAO;
import DAO.OcorrenciaDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import projeto_validacao.Model.CidadaoModel;
import projeto_validacao.Model.CidadaoTableModel;
import projeto_validacao.Model.OcorrenciaModel;
import projeto_validacao.Model.OcorrenciaTableModel;

/**
 *
 * @author Matteus
 */
public class MainJFrame extends javax.swing.JFrame {

    List<CidadaoModel> cidadaoList = new ArrayList<>();
    List<OcorrenciaModel> ocorrenciaList = new ArrayList<>();
    int selectedId;

    public MainJFrame() {
        initComponents();
        cidadaoList = CidadaoDAO.getCidadao();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("CIDAD�OS"), c);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CIDAD�OS");
        this.setSize(new Dimension(1366, 768));
        JTable table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setModel(new CidadaoTableModel(cidadaoList));
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(500, 200));
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 6;
        this.add(pane, c);
        JButton btnSaveCidadao = new JButton("Salvar Altera��es");
        JButton btnAddCidadao = new JButton("Novo Cidad�o");
        c.gridy = 3;
        c.gridx = 1;
        c.gridwidth = 2;
        this.add(btnSaveCidadao, c);
        c.gridx = 5;
        this.add(btnAddCidadao, c);
        JTable table2 = new JTable();
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table2.setModel(new OcorrenciaTableModel(ocorrenciaList));
        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setPreferredSize(new Dimension(500, 200));
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 6;
        c.gridx = 0;
        this.add(pane2, c);
        JButton btnSaveOcorrencia = new JButton("Salvar Altera��es");
        JButton btnAddOcorrencia = new JButton("Nova Ocorrencia");
        c.gridy = -5;
        c.gridx = 1;
        c.gridwidth = 2;
        this.add(btnSaveOcorrencia, c);
        c.gridx = 5;
        this.add(btnAddOcorrencia, c);
        this.setVisible(true);
        btnAddCidadao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cidadaoList.add(new CidadaoModel());
                table.setModel(new CidadaoTableModel(cidadaoList));
            }
        });

        btnAddOcorrencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocorrenciaList.add(new OcorrenciaModel());
                table2.setModel(new OcorrenciaTableModel(ocorrenciaList));
            }
        });
        btnSaveOcorrencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocorrenciaList.removeIf(s -> s.getId() > 0);
                table2.setModel(new OcorrenciaTableModel(ocorrenciaList));
                for (OcorrenciaModel ocorrencia : ocorrenciaList) {
                    if (ocorrencia.getLocal() == null || ocorrencia.getLocal().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Local  n�o pode ser vazio.",
                                "Alerta!",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (ocorrencia.getData() == null || ocorrencia.getData().toString().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Data n�o pode ser vazia.",
                                "Alerta!",
                                JOptionPane.WARNING_MESSAGE);

                    } else if (ocorrencia.getDescricao() == null || ocorrencia.getDescricao().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Descri��o n�o pode ser vazio.",
                                "Alerta!",
                                JOptionPane.WARNING_MESSAGE);

                    } else {
                        ocorrencia.setCidadaoId(selectedId);
                        OcorrenciaDAO.insertOcorrencia(ocorrencia);
                    }

                }
                ocorrenciaList = OcorrenciaDAO.getOcorrencias(selectedId);
                table2.setModel(new OcorrenciaTableModel(ocorrenciaList));

            }
        });

        btnSaveCidadao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cidadaoList.removeIf(s -> s.getId() > 0);
                table.setModel(new CidadaoTableModel(cidadaoList));
                for (CidadaoModel cidadao : cidadaoList) {
                    if (cidadao.getTelefone() == null || cidadao.getTelefone().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Telefone  n�o pode ser vazio.",
                                "Alerta!",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (cidadao.getEndereco() == null || cidadao.getEndereco().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Endere�o n�o pode ser vazio.",
                                "Alerta!",
                                JOptionPane.WARNING_MESSAGE);

                    } else {
                        CidadaoDAO.insertCidadao(cidadao);
                    }

                }
                cidadaoList = CidadaoDAO.getCidadao();
                table.setModel(new CidadaoTableModel(cidadaoList));

            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                if (SwingUtilities.isRightMouseButton(me) == true) {
                    int input = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cidadao?", "Excluir Cidad�o", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        CidadaoDAO.deleteCidadao(selectedId);
                        cidadaoList = CidadaoDAO.getCidadao();
                        table.setModel(new CidadaoTableModel(cidadaoList));
                        ocorrenciaList = OcorrenciaDAO.getOcorrencias(selectedId);
                        table2.setModel(new OcorrenciaTableModel(ocorrenciaList));
                    }
                } else {
                    selectedId = cidadaoList.get(row).getId();
                    ocorrenciaList = OcorrenciaDAO.getOcorrencias(selectedId);
                    table2.setModel(new OcorrenciaTableModel(ocorrenciaList));

                }
            }
        });

        table2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me) == true) {
                    int input = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a ocorrencia?", "Excluir Cidad�o", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        int row = table.rowAtPoint(me.getPoint());
                        OcorrenciaDAO.delete(ocorrenciaList.get(row).getId());
                        ocorrenciaList = OcorrenciaDAO.getOcorrencias(selectedId);
                        table2.setModel(new OcorrenciaTableModel(ocorrenciaList));
                    }
                }
            }
        });

      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
