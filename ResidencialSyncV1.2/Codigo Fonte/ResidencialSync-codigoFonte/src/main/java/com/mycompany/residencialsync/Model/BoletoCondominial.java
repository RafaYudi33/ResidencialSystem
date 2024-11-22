/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import jakarta.persistence.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.cglib.core.Local;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author rafay
 */

@SuppressWarnings("All")
@Entity(name = "boleto")
public class BoletoCondominial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorMulta;
    private double percentagemMensalJuros;

    private LocalDateTime dataVencimento;

    private double valorTotalContaAgua;

    private double taxaFinal;

    private double taxaBase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propriedade_id", foreignKey = @ForeignKey(name = "FK_propriedade_boleto"))
    private Propriedade propriedade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condominio_id", foreignKey = @ForeignKey(name = "FK_condominio_boleto"))
    private Condominio condominio;

    public BoletoCondominial(){}

    public BoletoCondominial(double valorMulta, double percentagemMensalJuros, LocalDateTime dataVencimento,
                             double taxaBase, double valorTotalContaAgua,
                             Propriedade propriedade, Condominio condominio
    ) {
        this.valorMulta = valorMulta;
        this.percentagemMensalJuros = percentagemMensalJuros;
        this.dataVencimento = dataVencimento;
        this.valorTotalContaAgua = valorTotalContaAgua;
        this.taxaBase = taxaBase;
        this.propriedade = propriedade;
        this.condominio = condominio;
    }

    public void calcularTaxaFinal(Integer qtdResidencias){
        double taxaFinalCalculada;
        int qtdReservas30dias = propriedade.getQtdReservasUltimos30dias();
        if (propriedade instanceof UnidadeResidencial){
            taxaFinalCalculada = ((this.valorTotalContaAgua/qtdResidencias)+this.taxaBase) + (10.0 * qtdReservas30dias);
        }else{
            taxaFinalCalculada = this.taxaBase + (10.0 * qtdReservas30dias);
        }
        setTaxaFinal(taxaFinalCalculada);
    }

    public void gerarPDF(){

        var dataAtual = LocalDateTime.now();
        var diaAtual = LocalDateTime.now().getDayOfMonth();
        var mesAtual = LocalDateTime.now().getMonthValue();
        var anoAtual = LocalDateTime.now().getYear();

        Datas datas = Datas.novasDatas()
                .comDocumento(diaAtual, mesAtual, anoAtual)
                .comProcessamento(diaAtual, mesAtual, anoAtual)
                .comVencimento(
                        this.dataVencimento.getDayOfMonth(),
                        this.dataVencimento.getMonthValue(),
                        this.dataVencimento.getYear()
                );

        Emissor emissor = Emissor.novoEmissor()
                .comCedente(condominio.getNome())
                .comAgencia(condominio.getAgencia())
                .comDigitoAgencia(condominio.getDigitoAgencia())
                .comContaCorrente(condominio.getContaCorrente())
                .comNumeroConvenio(condominio.getNumeroConvenio())
                .comDigitoContaCorrente(condominio.getDigitoContaCorrente())
                .comCarteira(condominio.getNumeroCarteira())
                .comNossoNumero(condominio.getNossoNumero());

        var proprietario = getPropriedade().getProprietario();
        var propriedade = getPropriedade();
        Sacado sacado = Sacado.novoSacado()
                .comNome(proprietario.getNome())
                .comCpf(proprietario.getCpf())
                .comEndereco(propriedade.getLogradouro())
                .comBairro(propriedade.getBairro())
                .comCep(propriedade.getCep())
                .comCidade(propriedade.getCidade())
                .comUf(propriedade.getUf());

        Banco banco = new Bradesco();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comDescricoes("descricao 1")
                .comEmissor(emissor)
                .comSacado(sacado)
                .comValorBoleto(this.taxaFinal)
                .comNumeroDoDocumento("1234")
                .comInstrucoes("Porcentagem Mensal Juros: "+Double.toString(percentagemMensalJuros))
                .comLocaisDePagamento("local 1", "local 2")
                .comNumeroDoDocumento("4343")
                .comValorMulta(String.valueOf(this.valorMulta));

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

        byte[] bPDF = gerador.geraPDF();

        Random rand = new Random();

        String caminhoArquivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + propriedade.getLogradouro()+propriedade.getNumero()+"boleto.pdf";

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bPDF);
             PDDocument document = PDDocument.load(byteArrayInputStream)) {
             document.save(caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Long getId() {
        return id;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

    public double getPercentagemMensalJuros() {
        return percentagemMensalJuros;
    }

    public void setPercentagemMensalJuros(double percentagemMensalJuros) {
        this.percentagemMensalJuros = percentagemMensalJuros;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorTotalContaAgua() {
        return valorTotalContaAgua;
    }

    public void setValorTotalContaAgua(double valorTotalContaAgua) {
        this.valorTotalContaAgua = valorTotalContaAgua;
    }

    public double getTaxaFinal() {
        return taxaFinal;
    }

    public void setTaxaFinal(double taxaFinal) {
        this.taxaFinal = taxaFinal;
    }

    public double getTaxaBase() {
        return taxaBase;
    }

    public void setTaxaBase(double taxaBase) {
        this.taxaBase = taxaBase;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
}
