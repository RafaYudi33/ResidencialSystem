/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.persistence.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static java.awt.SystemColor.text;

/**
 *
 * @author rafay
 */

@Entity
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEhora1;

    @ManyToOne
    @JoinColumn(name = "unidade_residencial_id", foreignKey = @ForeignKey(name = "FK_residencia_visita"))
    private UnidadeResidencial unidade;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "visitante_id", foreignKey = @ForeignKey(name = "FK_visitante_visita"))
    private Visitante visitante; 

    public Visita(){
        this.visitante = new Visitante();
    }
    
    public Visita(LocalDateTime dataEhora1,
            String placaVeiculoVisitante, String cpfVisitante,
            UnidadeResidencial unidade
    ) {
        this.dataEhora1 = dataEhora1;
        this.unidade = unidade;
        this.visitante = new Visitante(placaVeiculoVisitante, cpfVisitante);
    }


    public String gerarQrCode(){
        String qrCodeText = "CPF: " + visitante.getCpf() + "\nData Visita: " + dataEhora1 +
                "\nData Expiração: " + dataEhora1.plusDays(1L);
        String filePath = System.getProperty("user.home") + "/Downloads/" + visitante.getPlacaVeiculo() + "qrcode.png";
        int qrCodeSize = 300;
        int imageWidth = 300;
        int imageHeight = 400;

        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            BufferedImage combinedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = combinedImage.createGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, imageWidth, imageHeight);
            g.drawImage(qrCodeImage, 0, 0, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String additionalText = "CPF: " + visitante.getCpf() + "\nData Visita: " + dataEhora1 +
                    "\nExpira em: " + dataEhora1.plusDays(1L);

            String[] lines = additionalText.split("\n");
            int y = qrCodeSize + 20;
            for (String line : lines) {
                g.drawString(line, 10, y);
                y += 20;
            }

            g.dispose();
            File outputFile = new File(filePath);
            ImageIO.write(combinedImage, "png", outputFile);
            System.out.println("QR Code gerado com informações em: " + filePath);
            return filePath;
        } catch (WriterException | IOException e) {
           throw new RuntimeException("Erro ao gerar QrCode!");
        }
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }



    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "dataEhora1=" + dataEhora1 +
                ", unidade=" + unidade.getCep() +
                ", visitante=" + visitante.getCpf() +
                '}';
    }

    public LocalDateTime getDataEhora1() {
        return dataEhora1;
    }

    public void setDataEhora1(LocalDateTime dataEhora1) {
        this.dataEhora1 = dataEhora1;
    }

    public UnidadeResidencial getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeResidencial unidade) {
        this.unidade = unidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visita visita)) return false;
        return Objects.equals(getId(), visita.getId()) && Objects.equals(getDataEhora1(), visita.getDataEhora1()) && Objects.equals(getUnidade(), visita.getUnidade()) && Objects.equals(getVisitante(), visita.getVisitante());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataEhora1(), getUnidade(), getVisitante());
    }
}
