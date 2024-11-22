/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorDeReservas;
import com.mycompany.residencialsync.Model.AreaDeLazer;
import com.mycompany.residencialsync.Model.Propriedade;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import com.mycompany.residencialsync.Model.UnidadeResidencial;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author rafay
 */
@SuppressWarnings("DataFlowIssue")
public class TelaReservasAreasDeLazer extends JFrame {
    private JComboBox<AreaDeLazer> comboAreas;
    private JComboBox<Propriedade> comboPropriedades;
    private JTextField cpfMoradorField;
    private JTextField dataHoraField;
    private JComboBox<Integer> duracaoCombo;
    private ControladorDeReservas controlador;

    public TelaReservasAreasDeLazer(SistemaCondominio sistema) {
        setTitle("Realizar Reserva");
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.controlador = sistema.getControladorReservas();
        initComponents();
        layoutComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        comboAreas = new JComboBox<>();
        comboPropriedades = new JComboBox<>();
        cpfMoradorField = new JTextField(15);
        dataHoraField = new JTextField("yyyy-MM-dd HH:mm", 15);

        duracaoCombo = new JComboBox<>();
        for (int i = 1; i <= 5; i++) {
            duracaoCombo.addItem(i);
        }

        List<AreaDeLazer> areas = controlador.obterAreasDeLazer();
        for (AreaDeLazer area : areas) {
            comboAreas.addItem(area);
        }

        List<Propriedade> propriedades = controlador.listarPropriedades();
        for (Propriedade propriedade : propriedades) {
            comboPropriedades.addItem(propriedade);
        }
    }

    private void layoutComponents() {
        // Painel principal com espaçamento e bordas
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel do formulário
        JPanel formPanel = new JPanel();
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblArea = new JLabel("Área de Lazer:");
        JLabel lblPropriedade = new JLabel("Propriedade:");
        JLabel lblCPF = new JLabel("CPF do Morador:");
        JLabel lblDataHora = new JLabel("Data e Hora:");
        JLabel lblDuracao = new JLabel("Duração (horas):");

        JButton btnSalvar = new JButton("Confirmar Reserva");
        btnSalvar.setPreferredSize(new Dimension(200, 30));
        btnSalvar.addActionListener(e -> salvarReserva());

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblArea)
                        .addComponent(lblPropriedade)
                        .addComponent(lblCPF)
                        .addComponent(lblDataHora)
                        .addComponent(lblDuracao))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(comboAreas, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboPropriedades, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpfMoradorField, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataHoraField, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(duracaoCombo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblArea)
                        .addComponent(comboAreas))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPropriedade)
                        .addComponent(comboPropriedades))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCPF)
                        .addComponent(cpfMoradorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataHora)
                        .addComponent(dataHoraField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDuracao)
                        .addComponent(duracaoCombo))
        );

        // Painel para o botão
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        // Adiciona o formulário e botão ao painel principal
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Define o painel principal como conteúdo da janela
        setContentPane(mainPanel);
    }

    private void salvarReserva() {
        AreaDeLazer areaSelecionada = (AreaDeLazer) comboAreas.getSelectedItem();
        Propriedade propriedadeSelecionada = (Propriedade) comboPropriedades.getSelectedItem();
        String cpfMorador = cpfMoradorField.getText();
        String dataHoraString = dataHoraField.getText();
        int duracao = (int) duracaoCombo.getSelectedItem();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dataHoraInDate = sdf.parse(dataHoraString);
            LocalDateTime dataHora = dataHoraInDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            var reserva = controlador.realizarReserva(cpfMorador, areaSelecionada, dataHora, duracao, propriedadeSelecionada);
            controlador.gerarComprovantePDF(reserva);

            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            setVisible(false);
        } catch (Exception ex) {
            String message = ex.getMessage();

            JOptionPane.showMessageDialog(this, message,
                    "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

}
