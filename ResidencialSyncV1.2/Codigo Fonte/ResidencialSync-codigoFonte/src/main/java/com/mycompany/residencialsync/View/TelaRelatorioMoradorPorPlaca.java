/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorMoradorResidencia;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
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
public class TelaRelatorioMoradorPorPlaca extends JFrame{
     private JTextField placaField;
    private JButton btnGerarRelatorio;
    private JTextArea relatorioArea;
    private JScrollPane scrollPane;
    private ControladorMoradorResidencia controlador;

    public TelaRelatorioMoradorPorPlaca(SistemaCondominio sistema) {
        setTitle("Relatório de Moradores por Placa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.controlador = sistema.getControladorMoradorResidencia();
        initComponents();
        layoutComponents();

        setVisible(true);

    }

    private void initComponents() {
        placaField = new JTextField(10);
        btnGerarRelatorio = new JButton("Gerar Relatório");
        relatorioArea = new JTextArea(15, 50);
        relatorioArea.setEditable(false);
        scrollPane = new JScrollPane(relatorioArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        btnGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
    }

    private void layoutComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputsPanel = new JPanel();

        inputsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputsPanel.add(new JLabel("Insira a Placa do Veículo:"));
        inputsPanel.add(placaField);
        inputsPanel.add(btnGerarRelatorio);

        panel.add(inputsPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void gerarRelatorio() {
        try {
            // Obter a placa do veículo do JTextField
            String placa = placaField.getText();
            System.out.println(placa);
            if (placa == null || placa.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira uma placa válida.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            String relatorio = controlador.listarMoradorPorVeiculo(placa).toString();

            // Exibir o relatório na JTextArea
            relatorioArea.setText(relatorio);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
