package com.mycompany.residencialsync.ServicosExternos;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mycompany.residencialsync.Model.Visita;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ServiceGeradorQrCode {

    public String gerarQrCode(Visita visita) {

        String qrCodeText = montarString(visita);
        String filePath = montarCaminho(visita);
        int qrCodeSize = 300;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);
            gerarImagemDoQrCode(bitMatrix, visita, qrCodeSize, filePath);
            return filePath;
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Erro ao gerar QrCode!");
        }
    }

    private String montarString(Visita visita) {
        return "CPF: " + visita.getVisitante().getCpf() + "\nData Visita: " + visita.getDataEhora1() +
                "\nData Expiração: " + visita.getDataEhora1().plusDays(1L);
    }

    private String montarCaminho(Visita visita) {
        return System.getProperty("user.home") + "/Downloads/" + visita.getVisitante().getPlacaVeiculo() + "qrcode.png";
    }

    private void gerarImagemDoQrCode(BitMatrix bitMatrix, Visita visita, Integer qrCodeSize, String filePath) throws IOException {
        int imageWidth = 300;
        int imageHeight = 400;

        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        BufferedImage combinedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = combinedImage.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imageWidth, imageHeight);
        g.drawImage(qrCodeImage, 0, 0, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        String additionalText = "CPF: " + visita.getVisitante().getCpf() + "\nData Visita: " + visita.getDataEhora1() +
                "\nExpira em: " + visita.getDataEhora1().plusDays(1L);

        String[] lines = additionalText.split("\n");
        int y = qrCodeSize + 20;
        for (String line : lines) {
            g.drawString(line, 10, y);
            y += 20;
        }

        g.dispose();
        File outputFile = new File(filePath);
        ImageIO.write(combinedImage, "png", outputFile);
    }
}
