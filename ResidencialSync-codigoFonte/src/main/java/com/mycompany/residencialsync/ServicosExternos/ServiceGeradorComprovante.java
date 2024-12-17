package com.mycompany.residencialsync.ServicosExternos;

import com.mycompany.residencialsync.Model.Reserva;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class ServiceGeradorComprovante {

    public void gerarComprovante(Reserva reserva) {
        try (PDDocument document = new PDDocument()) {
            PDType0Font font = carregarFonte(document);
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                escreverTitulo(contentStream, font);
                escreverDadosReserva(contentStream, font, reserva);
            }


            Path filePath = gerarCaminhoArquivo(reserva);
            document.save(filePath.toFile());
            System.out.println("PDF salvo em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    private PDType0Font carregarFonte(PDDocument document) throws IOException {
        InputStream fontStream = getClass().getResourceAsStream("/Helvetica.ttf");
        if (fontStream == null) {
            throw new IOException("Fonte não encontrada: " + "/Helvetica.ttf");
        }
        return PDType0Font.load(document, fontStream);
    }

    private void escreverTitulo(PDPageContentStream contentStream, PDType0Font font) throws IOException {
        contentStream.setFont(font, 16);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(50, 750);
        contentStream.showText("Comprovante de Reserva");
        contentStream.newLine();
        contentStream.newLine();
    }

    private void escreverDadosReserva(PDPageContentStream contentStream, PDType0Font font, Reserva reserva) throws IOException {
        contentStream.setFont(font, 12);

        contentStream.showText("Data e Hora: " + reserva.getDataEhora());
        contentStream.newLine();
        contentStream.showText("Duração da Reserva (horas): " + reserva.getDuracaoReserva());
        contentStream.newLine();
        contentStream.newLine();

        if (reserva.getAreaDeLazer() != null) {
            contentStream.showText("Área de Lazer: " + reserva.getAreaDeLazer().getNome());
            contentStream.newLine();
            contentStream.showText("Tamanho: " + reserva.getAreaDeLazer().getTamanho());
            contentStream.newLine();
            contentStream.showText("Tipo: " + reserva.getAreaDeLazer().getTipo());
            contentStream.newLine();
            contentStream.newLine();
        }

        if (reserva.getPropriedade() != null) {
            contentStream.showText("Propriedade do reservante: " +
                    reserva.getPropriedade().getLogradouro() + ", " +
                    reserva.getPropriedade().getCidade() + ", " +
                    reserva.getPropriedade().getUf() + " - " +
                    reserva.getPropriedade().getCep());
            contentStream.newLine();
            contentStream.newLine();
        }

        contentStream.endText();
    }

    private Path gerarCaminhoArquivo(Reserva reserva) {
        Random random = new Random();
        return Paths.get(
                System.getProperty("user.home"),
                "Downloads",
                "comprovante-" + random.nextLong() + "-" + reserva.getAreaDeLazer().getNome() + ".pdf"
        );
    }
}
