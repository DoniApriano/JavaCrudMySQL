package com.crud.statistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePanel {

    public UpdatePanel() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idSiswa,nis,nama,kelas;
                idSiswa = textFieldUpIdSiswa.getText();
                nis = textFieldNisUpdate.getText();
                nama = textFieldNamaUpdate.getText();
                kelas = textFieldKelasUpdate.getText();
                if (
                        !Objects.equals(nis,"") && !Objects.equals(nama,"") && !Objects.equals(kelas,"")
                ) {
                    try {
                        preparedStatement = Connector.ConnectDB().prepareStatement("update siswa set nis = ?, nama = ?, kelas = ? where id_siswa = ?;");
                        preparedStatement.setString(1, nis);
                        preparedStatement.setString(2, nama);
                        preparedStatement.setString(3, kelas);
                        preparedStatement.setString(4, idSiswa);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Update data sukses");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Masukkan Input terlebih dahulu BLOK");
                }
            }
        });
        batalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getUpdatePanel() {
        return mainUpdatePanel;
    }

    private PreparedStatement preparedStatement;

    private JPanel mainUpdatePanel;
    private JLabel jTitleUpdatePanel;
    private JTextField textFieldUpIdSiswa;
    private JTextField textFieldNisUpdate;
    private JTextField textFieldNamaUpdate;
    private JTextField textFieldKelasUpdate;
    private JButton updateButton;
    private JButton batalButton;
    private JLabel jLabelSiswa;
    private JLabel jNisLabel;
    private JLabel jNamaLabel;
    private JLabel jKelasLabel;
}
