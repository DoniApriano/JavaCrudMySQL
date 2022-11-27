package com.crud.statistic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataInterface {

    public DataInterface() {
        tambahDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nis,nama,kelas;
                nis = textFieldNis.getText();
                nama = textFieldNama.getText();
                kelas = textFieldKelas.getText();

                try {
                    preparedStatement = Connector.ConnectDB().prepareStatement("insert into siswa (nis, nama, kelas) values (?,?,?);");
                    preparedStatement.setString(1,nis);
                    preparedStatement.setString(2,nama);
                    preparedStatement.setString(3,kelas);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
                } catch (SQLException err) {
                    Logger.getLogger(DataInterface.class.getName()).log(Level.SEVERE, null, err);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(DataInterface::createUpdateGUI);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        showData();
        return mainPanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData() {
        try {
            Object[] columnTitle = {"Data Siswa", "Nis", "Nama", "Kelas"};
            tableModel = new DefaultTableModel(null,columnTitle);
            jTable.setModel(tableModel);

            Connection connection =  Connector.ConnectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            resultSet = statement.executeQuery("select * from siswa");
            while (resultSet.next()) {
                Object[] data = {
                        resultSet.getString("id_siswa"),
                        resultSet.getString("nis"),
                        resultSet.getString("nama"),
                        resultSet.getString("kelas")
                };
                tableModel.addRow(data);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    private static void createUpdateGUI() {
        UpdatePanel updateUI = new UpdatePanel();
        JPanel updateRoot = updateUI.getUpdatePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(updateRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }


    private JTextField textFieldNis;
    private JTextField textFieldNama;
    private JTextField textFieldKelas;
    private JButton tambahDataButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JPanel jThirdPanel;
    private JLabel jLabelNis;
    private JLabel jLabelNama;
    private JLabel jLabelAlamat;
    private JTable jTable;
    private JPanel mainPanel;
}
