package com.mycompany.residencialsync.ServicosExternos;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import com.mycompany.residencialsync.Model.BoletoCondominial;
import com.mycompany.residencialsync.Model.Condominio;
import com.mycompany.residencialsync.Model.Propriedade;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ServiceGeradorBoletos {

    public void gerarBoleto(BoletoCondominial boletoCondominial) {
        var diaAtual = LocalDateTime.now().getDayOfMonth();
        var mesAtual = LocalDateTime.now().getMonthValue();
        var anoAtual = LocalDateTime.now().getYear();

        Condominio condominio = boletoCondominial.getCondominio();

        Datas datas = Datas.novasDatas()
                .comDocumento(diaAtual, mesAtual, anoAtual)
                .comProcessamento(diaAtual, mesAtual, anoAtual)
                .comVencimento(
                        boletoCondominial.getDataVencimento().getDayOfMonth(),
                        boletoCondominial.getDataVencimento().getMonthValue(),
                        boletoCondominial.getDataVencimento().getYear()
                );

        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario(condominio.getNome())
                .comAgencia(Integer.toString(condominio.getAgencia()))
                .comDigitoAgencia(Character.toString(condominio.getAgencia()))
                .comDocumento(condominio.getCnpj())
                .comNumeroConvenio(Integer.toString(condominio.getNumeroConvenio()))
                .comCarteira(Integer.toString(condominio.getNumeroCarteira()))
                .comNossoNumero(Integer.toString(condominio.getNossoNumero()));

        var propriedade = boletoCondominial.getPropriedade();
        var proprietario = propriedade.getProprietario();

        Endereco endereco  = Endereco.novoEndereco()
                .comBairro(propriedade.getBairro())
                .comCep(propriedade.getCep())
                .comCidade(propriedade.getCidade())
                .comLogradouro(propriedade.getLogradouro())
                .comUf(propriedade.getUf());

        Pagador pagador = Pagador.novoPagador()
                .comNome(proprietario.getNome())
                .comDocumento(proprietario.getCpf())
                .comEndereco(endereco);

        Banco banco = new Bradesco();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comDescricoes("descricao 1")
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(boletoCondominial.getTaxaFinal())
                .comNumeroDoDocumento("1234")
                .comLocaisDePagamento("local 1", "local 2")
                .comNumeroDoDocumento("4343")
                .comValorMulta(BigDecimal.valueOf(boletoCondominial.getValorMulta()));

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        byte[] bPDF = gerador.geraPDF();
        salvarArquivo(bPDF, montarCaminho(propriedade));
    }

    private void salvarArquivo(byte[] bpdf, String caminhoArquivo) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bpdf);
             PDDocument document = PDDocument.load(byteArrayInputStream)) {
            document.save(caminhoArquivo);
        } catch (IOException e) {
           throw new RuntimeException("Erro em salvar boleto");
        }
    }

    private String montarCaminho(Propriedade propriedade) {
        return System.getProperty("user.home") +
                File.separator +
                "Downloads" +
                File.separator +
                propriedade.getLogradouro()+
                propriedade.getNumero()
                +"boleto.pdf";
    }

}
