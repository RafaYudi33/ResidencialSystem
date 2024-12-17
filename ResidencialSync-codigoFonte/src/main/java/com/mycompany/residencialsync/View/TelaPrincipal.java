package com.mycompany.residencialsync.View;

import com.mycompany.residencialsync.Config.AppConfig;
import com.mycompany.residencialsync.Config.JpaConfig;
import com.mycompany.residencialsync.Model.SistemaCondominio;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private static SistemaCondominio sistema;

    public TelaPrincipal(ApplicationContext context) {
        sistema = context.getBean(SistemaCondominio.class);
        String imagePath = "src/main/resources/images/img.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(backgroundPanel);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Sistema Residencial");
        setResizable(false);


        JMenuBar menuBar = new JMenuBar();


        JMenu menuReservas = new JMenu("Reservas de Área de Lazer");
        JMenuItem reservarArea = new JMenuItem("Reservar área de lazer");
        JMenuItem consultarAreasDisponiveis = new JMenuItem("Consultar áreas de lazer disponíveis com base na data");
        menuReservas.add(reservarArea);
        menuReservas.add(consultarAreasDisponiveis);


        JMenu menuBoletos = new JMenu("Boletos");
        JMenuItem emitirBoletos = new JMenuItem("Emitir Boletos para todos moradores");
        menuBoletos.add(emitirBoletos);


        JMenu menuRelatorios = new JMenu("Relatórios");
        JMenuItem relatorioMoradores = new JMenuItem("Produzir relatórios dos moradores de uma determinada unidade residencial");
        JMenuItem relatorioMoradorPlaca = new JMenuItem("Gerar relatório completo de um morador a partir da placa de seu veículo");
        menuRelatorios.add(relatorioMoradores);
        menuRelatorios.add(relatorioMoradorPlaca);


        JMenu menuVisitas = new JMenu("Visitas");
        JMenuItem agendarVisita = new JMenuItem("Agendar visitas para uma unidade residencial");
        menuVisitas.add(agendarVisita);


        menuBar.add(menuReservas);
        menuBar.add(menuBoletos);
        menuBar.add(menuRelatorios);
        menuBar.add(menuVisitas);


        setJMenuBar(menuBar);

        reservarArea.addActionListener(e -> new TelaReservasAreasDeLazer(sistema).setVisible(true));
        consultarAreasDisponiveis.addActionListener(e -> new TelaConsultarAreasDeLazerData(sistema).setVisible(true));
        emitirBoletos.addActionListener(e -> new TelaGerarBoletos(sistema).setVisible(true));
        relatorioMoradores.addActionListener(e -> new TelaRelatorioMoradoresDeUmaUnidade(sistema).setVisible(true));
        relatorioMoradorPlaca.addActionListener(e -> new TelaRelatorioMoradorPorPlaca(sistema).setVisible(true));
        agendarVisita.addActionListener(e -> new TelaAgendarReservaVisita(sistema).setVisible(true));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class, AppConfig.class);
        SwingUtilities.invokeLater(() -> new TelaPrincipal(context).setVisible(true));


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sistema.fechamentoSistema();
            context.close();
        }));
    }
}
