/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorDeVisitas;
import com.mycompany.residencialsync.Controladores.ControladorMoradorResidencia;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author rafay
 */
public class TelaAgendarReservaVisita extends JFrame{
    
    private JTextField cpfField;
    private JTextField placaCarroField;
    private JTextField dataEhora1Field;
    private JComboBox<UnidadeResidencial> unidadesComboBox;
    private JButton btnAgendarReserva;
    private ControladorDeVisitas controladorVisita;
    private List<UnidadeResidencial> unidadesResidenciais;

    public TelaAgendarReservaVisita(SistemaCondominio sistema) {
        setTitle("Agendar Reserva de Visita");
        setSize(540, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.controladorVisita = sistema.getControladorVisitas();
        ControladorMoradorResidencia controladorMoradorResidencia = sistema.getControladorMoradorResidencia();
        this.unidadesResidenciais = controladorMoradorResidencia.obterUnidadesResidenciais();
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        layoutComponents();
        setVisible(true);
    }

    private void initComponents() {
        cpfField = new JTextField(26);
        placaCarroField = new JTextField(26);
        dataEhora1Field = new JTextField("yyyy-MM-dd HH:mm", 26);
        unidadesComboBox = new JComboBox<>();
        btnAgendarReserva = new JButton("Agendar Reserva");

        btnAgendarReserva.addActionListener(e -> agendarReserva());

        DefaultComboBoxModel<UnidadeResidencial> model = new DefaultComboBoxModel<>();
        for (UnidadeResidencial unidade : unidadesResidenciais) {
            model.addElement(unidade);
        }
        unidadesComboBox.setModel(model);
        System.out.println(unidadesComboBox.getSize());
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblPlacaCarro = new JLabel("Placa do Carro:");
        JLabel lblDataHora = new JLabel("Data da visita (yyyy-MM-dd HH:mm):");
        JLabel lblUnidadeResidencial = new JLabel("Selecione a Unidade Residencial");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblCpf)
                                .addComponent(lblPlacaCarro)
                                .addComponent(lblDataHora)
                                .addComponent(lblUnidadeResidencial))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cpfField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(placaCarroField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(dataEhora1Field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(unidadesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgendarReserva, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCpf)
                                .addComponent(cpfField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPlacaCarro)
                                .addComponent(placaCarroField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDataHora)
                                .addComponent(dataEhora1Field))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUnidadeResidencial)
                                .addComponent(unidadesComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAgendarReserva))
        );

        add(panel);
    }

    private void agendarReserva() {
        try {
            String cpf = cpfField.getText();
            String placaCarro = placaCarroField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataHoraInDate = sdf.parse(dataEhora1Field.getText());

            LocalDateTime dataHora = dataHoraInDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            UnidadeResidencial unidadeSelecionada = (UnidadeResidencial) unidadesComboBox.getSelectedItem();

            if (unidadeSelecionada == null) {
                JOptionPane.showMessageDialog(this,
                        "Unidade residencial não encontrada.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var filPath = controladorVisita.agendarVisita(cpf, placaCarro, dataHora, unidadeSelecionada);
            
            JOptionPane.showMessageDialog(this,
                    "Reserva agendada com sucesso! QrCode da visita salvo em: "+filPath,
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao agendar reserva ",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
