/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author rafay
 */
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private LocalDateTime dataEhora;
    private int duracaoReserva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_de_lazer_id", foreignKey = @ForeignKey(name = "FK_area_reserva"))
    private AreaDeLazer areaDeLazer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propriedade_id", foreignKey = @ForeignKey(name = "FK_propriedade_reserva"))
    private Propriedade propriedade;

    public Reserva(){}

    public Reserva(LocalDateTime dataEhora, int duracaoReserva, AreaDeLazer areaDeLazer, Propriedade propriedade) {
        this.dataEhora = dataEhora;
        this.duracaoReserva = duracaoReserva;
        this.areaDeLazer = areaDeLazer;
        this.propriedade = propriedade;
    }

    public void gerarPDF() {
        try (PDDocument document = new PDDocument()) {

            InputStream fontStream = getClass().getResourceAsStream("/Helvetica.ttf");
            PDType0Font font = PDType0Font.load(document, fontStream);
            PDPage page = new PDPage();
            document.addPage(page);


            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                contentStream.setFont(font, 16);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 700);

                // Título do PDF
                contentStream.showText("Comprovante de Reserva");
                contentStream.newLine();
                contentStream.newLine();

                // Configuração de fonte para o corpo do texto
                contentStream.setFont(font, 12);
                contentStream.newLine();
                contentStream.showText("Data e Hora: " + dataEhora);
                contentStream.newLine();
                contentStream.showText("Duração da Reserva (horas): " + duracaoReserva);
                contentStream.newLine();
                contentStream.newLine();

                // Dados da área de lazer
                if (areaDeLazer != null) {
                    contentStream.showText("Área de Lazer: " + areaDeLazer.getNome());
                    contentStream.newLine();
                    contentStream.showText("Tamanho: " + areaDeLazer.getTamanho());
                    contentStream.newLine();
                    contentStream.showText("Tipo: " + areaDeLazer.getTipo());
                    contentStream.newLine();
                    contentStream.newLine();
                }

                // Dados da propriedade
                if (propriedade != null) {
                    contentStream.showText("Propriedade do reservante: " + propriedade.getLogradouro() + ", " +
                            propriedade.getCidade() + ", " + propriedade.getUf() +
                            " - " + propriedade.getCep());
                    contentStream.newLine();
                    contentStream.newLine();
                }
                contentStream.endText();
            }

            Random random = new Random();
            Path downloadPath = Paths.get(System.getProperty("user.home"), "Downloads", "comprovante - "+random.nextLong()+" - "+getAreaDeLazer().getNome()+".pdf");
            document.save(downloadPath.toFile());

            System.out.println("PDF salvo em: " + downloadPath);

        } catch (IOException e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    public LocalDateTime getDataEhora() {
        return dataEhora;
    }

    public void setDataEhora(LocalDateTime dataEhora) {
        this.dataEhora = dataEhora;
    }

    public int getDuracaoReserva() {
        return duracaoReserva;
    }

    public void setDuracaoReserva(int duracaoReserva) {
        this.duracaoReserva = duracaoReserva;
    }

    public AreaDeLazer getAreaDeLazer() {
        return areaDeLazer;
    }

    public void setAreaDeLazer(AreaDeLazer areaDeLazer) {
        this.areaDeLazer = areaDeLazer;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Reserva reserva)) return false;
        return getDuracaoReserva() == reserva.getDuracaoReserva() && Objects.equals(id, reserva.id) && Objects.equals(getDataEhora(), reserva.getDataEhora()) && Objects.equals(getAreaDeLazer(), reserva.getAreaDeLazer()) && Objects.equals(getPropriedade(), reserva.getPropriedade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDataEhora(), getDuracaoReserva(), getAreaDeLazer(), getPropriedade());
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataEhora=" + dataEhora +
                ", duracaoReserva=" + duracaoReserva +
                ", areaDeLazer=" + areaDeLazer +
                ", propriedade=" + propriedade +
                '}';
    }
}
