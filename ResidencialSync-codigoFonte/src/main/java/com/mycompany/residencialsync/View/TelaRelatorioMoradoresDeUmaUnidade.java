/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorMoradorResidencia;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author rafay
 */
public class TelaRelatorioMoradoresDeUmaUnidade extends JFrame {
    private JComboBox<UnidadeResidencial> unidadesComboBox;
    private JButton btnGerarRelatorio;
    private JTextArea relatorioArea;
    private JScrollPane scrollPane;
    private ControladorMoradorResidencia controlador;
    private List<UnidadeResidencial> unidadesResidenciais;

    public TelaRelatorioMoradoresDeUmaUnidade(SistemaCondominio sistema) {
        setTitle("Relatório de Moradores por Unidade");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.controlador = sistema.getControladorMoradorResidencia();
        this.unidadesResidenciais = controlador.obterUnidadesResidenciais();

        initComponents();
        layoutComponents();

        setVisible(true);
    }

    private void initComponents() {
        unidadesComboBox = new JComboBox<>();
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

        // Preencher o ComboBox com os objetos UnidadeResidencial
        DefaultComboBoxModel<UnidadeResidencial> model = new DefaultComboBoxModel<>();
        for (UnidadeResidencial unidade : unidadesResidenciais) {
            model.addElement(unidade);
        }
        unidadesComboBox.setModel(model);
    }

    private void layoutComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputsPanel = new JPanel();

        inputsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputsPanel.add(new JLabel("Selecione a Unidade Residencial "));
        inputsPanel.add(unidadesComboBox);
        inputsPanel.add(btnGerarRelatorio);

        panel.add(inputsPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnGerarRelatorio, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void gerarRelatorio() {
            relatorioArea.setText("");
            // Obter a unidade residencial selecionada no JComboBox
            UnidadeResidencial unidadeSelecionada = (UnidadeResidencial) unidadesComboBox.getSelectedItem();

            if (unidadeSelecionada == null) {
                JOptionPane.showMessageDialog(this,
                        "Unidade residencial não encontrada.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            String relatorio = controlador.listarMoradoresDeUmaUnidade(unidadeSelecionada).toString();
            relatorioArea.setText(relatorio);

        
    }

}
