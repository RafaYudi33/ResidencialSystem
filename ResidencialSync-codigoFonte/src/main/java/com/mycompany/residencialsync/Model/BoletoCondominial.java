/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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
