/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorDeReservas;
import com.mycompany.residencialsync.Model.AreaDeLazer;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import static java.awt.AWTEventMulticaster.add;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rafay
 */
public class TelaConsultarAreasDeLazerData extends JFrame {
    private JTextField dataField;
    private JButton btnConsultar;
    private JTextArea areaDeLazerInfo;
    private SistemaCondominio sistema;
    private ControladorDeReservas controlador;
    private JScrollPane scrollPane;

    public TelaConsultarAreasDeLazerData(SistemaCondominio sistema) {
        setTitle("Consultar Áreas de Lazer Disponíveis");
        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.controlador = sistema.getControladorReservas();
        this.sistema = sistema;
        initComponents();
        layoutComponents();

        setLocationRelativeTo(null);
    }

    private void initComponents() {
        dataField = new JTextField();
        dataField.setPreferredSize(new Dimension(100,20));
        btnConsultar = new JButton("Consultar");
        areaDeLazerInfo = new JTextArea(10, 30);
        areaDeLazerInfo.setEditable(false);

        scrollPane = new JScrollPane(areaDeLazerInfo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        btnConsultar.addActionListener(e -> consultarAreasDeLazer());
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Informe a Data (Formato: yyyy-MM-dd HH:mm:ss):"));
        inputPanel.add(dataField);
        inputPanel.add(btnConsultar);

        panel.add(inputPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Áreas de Lazer Disponíveis:"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    private void consultarAreasDeLazer() {
        areaDeLazerInfo.setText("");
        String dataString = dataField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date data = sdf.parse(dataString);
            LocalDateTime dataHora = data.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            List<AreaDeLazer> areasDisponiveis = controlador.listarAreasDeLazerDisponiveis(dataHora);

            if (areasDisponiveis.isEmpty()) {
                areaDeLazerInfo.setText("Não há áreas de lazer disponíveis para a data informada.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (AreaDeLazer area : areasDisponiveis) {
                    sb.append(area).append("\n\n");
                }
                areaDeLazerInfo.setText(sb.toString());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar áreas de lazer: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
