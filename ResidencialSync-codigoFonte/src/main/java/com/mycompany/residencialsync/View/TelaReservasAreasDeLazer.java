package com.mycompany.residencialsync.View;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.mycompany.residencialsync.Controladores.ControladorDeReservas;
import com.mycompany.residencialsync.Model.AreaDeLazer;
import com.mycompany.residencialsync.Model.Propriedade;
import com.mycompany.residencialsync.Model.SistemaCondominio;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class TelaReservasAreasDeLazer extends JFrame {
    private JComboBox<AreaDeLazer> comboAreas;
    private JComboBox<Propriedade> comboPropriedades;
    private JTextField cpfMoradorField;
    private JComboBox<Integer> duracaoCombo;
    private DateTimePicker dateTimePicker; // DateTimePicker para data e hora
    private ControladorDeReservas controlador;

    public TelaReservasAreasDeLazer(SistemaCondominio sistema) {
        setTitle("Realizar Reserva");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        this.controlador = sistema.getControladorReservas();
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        comboAreas = new JComboBox<>();
        comboPropriedades = new JComboBox<>();
        cpfMoradorField = new JTextField(15);
        dateTimePicker = new DateTimePicker(); // Componente para Data e Hora
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
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 10, 7, 10);

        JLabel lblArea = new JLabel("Área de Lazer:");
        JLabel lblPropriedade = new JLabel("Propriedade:");
        JLabel lblCPF = new JLabel("CPF do Reservante:");
        JLabel lblDataHora = new JLabel("Data e Hora:");
        JLabel lblDuracao = new JLabel("Duração (horas):");

        JButton btnSalvar = new JButton("Confirmar Reserva");
        btnSalvar.setPreferredSize(new Dimension(200, 35));
        btnSalvar.addActionListener(e -> salvarReserva());

        // Layout: Área de Lazer
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblArea, gbc);
        gbc.gridx = 1;
        panel.add(comboAreas, gbc);

        // Layout: Propriedade
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPropriedade, gbc);
        gbc.gridx = 1;
        panel.add(comboPropriedades, gbc);

        // Layout: CPF
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblCPF, gbc);
        gbc.gridx = 1;
        panel.add(cpfMoradorField, gbc);

        // Layout: Data e Hora
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblDataHora, gbc);
        gbc.gridx = 1;
        panel.add(dateTimePicker, gbc);

        // Layout: Duração
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(lblDuracao, gbc);
        gbc.gridx = 1;
        panel.add(duracaoCombo, gbc);

        // Layout: Botão
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSalvar, gbc);

        setContentPane(panel);
    }

    private void salvarReserva() {
        try {
            AreaDeLazer areaSelecionada = (AreaDeLazer) comboAreas.getSelectedItem();
            Propriedade propriedadeSelecionada = (Propriedade) comboPropriedades.getSelectedItem();
            String cpfMorador = cpfMoradorField.getText();
            LocalDateTime dataHora = dateTimePicker.getDateTimeStrict();
            int duracao = (int) duracaoCombo.getSelectedItem();

            var reserva = controlador.realizarReserva(cpfMorador, areaSelecionada, dataHora, duracao, propriedadeSelecionada);
            controlador.gerarComprovantePDF(reserva);

            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }
}
