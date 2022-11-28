package com.crud.statistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeletePanel {


    public DeletePanel() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idSiswa;
                idSiswa = textFieldIdSiswaDelete.getText();

                if (!Objects.equals(idSiswa,"")) {
                    try {
                        preparedStatement = Connector.ConnectDB().prepareStatement("DELETE FROM siswa WHERE id_siswa=?;");
                        preparedStatement.setString(1, idSiswa);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Masukkan id siswa terlebih dahulu");
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

    public JPanel getDeletePanel(){
        return deletePanel;
    }

    public PreparedStatement preparedStatement;

    private JTextField textFieldIdSiswaDelete;
    private JButton deleteButton;
    private JButton batalButton;
    private JLabel jIdSiswaLabel;
    private JPanel deletePanel;
}
