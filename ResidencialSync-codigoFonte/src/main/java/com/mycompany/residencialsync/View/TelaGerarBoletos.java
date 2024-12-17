/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Controladores.ControladorGeradorBoletos;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rafay
 */
public class TelaGerarBoletos extends JFrame{
    private JTextField valorMultaField;
    private JTextField porcentagemJurosField;
    private JTextField dataVencimentoField;
    private JTextField valorContaAguaField;
    private JTextField taxaBaseField;

    private JButton btnGerarBoleto;
    private ControladorGeradorBoletos controlador;
    private SistemaCondominio sistema;

    public TelaGerarBoletos(SistemaCondominio sistema) {
        setTitle("Gerar Boletos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.controlador = sistema.getControladorBoletosEtaxas();
        this.sistema = sistema;
        initComponents();
        layoutComponents();

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        valorMultaField = new JTextField(10);
        porcentagemJurosField = new JTextField(10);
        dataVencimentoField = new JTextField(10);
        valorContaAguaField = new JTextField(10);
        taxaBaseField = new JTextField(10);

        btnGerarBoleto = new JButton("Gerar Boleto");

        btnGerarBoleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gerarBoleto();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Valor da Multa:"));
        panel.add(valorMultaField);

        panel.add(new JLabel("Porcentagem de Juros Mensais:"));
        panel.add(porcentagemJurosField);

        panel.add(new JLabel("Data de Vencimento (YYYY-MM-DD):"));
        panel.add(dataVencimentoField);

        panel.add(new JLabel("Valor Total Conta de Água:"));
        panel.add(valorContaAguaField);

        panel.add(new JLabel("Taxa Base:"));
        panel.add(taxaBaseField);

        panel.add(new JLabel()); // Espaço vazio para alinhar o botão
        panel.add(btnGerarBoleto);

        add(panel, BorderLayout.CENTER);
    }

    private void gerarBoleto() throws IOException {
        try {
            float valorMulta = Float.parseFloat(valorMultaField.getText());
            float porcentagemJuros = Float.parseFloat(porcentagemJurosField.getText());
            LocalDate dataVencimento = LocalDate.parse(dataVencimentoField.getText());
            double valorContaAgua = Double.parseDouble(valorContaAguaField.getText());
            double taxaBase = Double.parseDouble(taxaBaseField.getText());

            controlador.gerarPDFBoletos(valorMulta, porcentagemJuros, dataVencimento.atStartOfDay(), taxaBase, valorContaAgua);

            JOptionPane.showMessageDialog(this,
                    "Os boletos foram gerados e salvos na pasta Downloads!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira valores válidos para os campos numéricos.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao gerar boletos: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
