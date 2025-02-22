/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaPropriedades;
import com.mycompany.residencialsync.Model.*;
import com.mycompany.residencialsync.ServicosExternos.ServiceGeradorBoletos;
import com.mycompany.residencialsync.ServicosExternos.awsServices.ServiceS3Uploader;
import com.mycompany.residencialsync.ServicosExternos.awsServices.ServiceSqsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafay
 */

@SuppressWarnings("FieldCanBeLocal")
@Component
@DependsOn("flyway")
public class CatalogoPropriedades {
    private List<Propriedade> propriedades;
    private final ServiceGeradorBoletos serviceGeradorBoletos;
    private final InterfaceJpaPropriedades interfaceJpaPropriedades;
    private final ServiceS3Uploader serviceS3Uploader;
    private final ServiceSqsSender serviceSqsSender;

    @Autowired
    public CatalogoPropriedades(ServiceGeradorBoletos serviceGeradorBoletos, InterfaceJpaPropriedades interfaceJpaPropriedades, ServiceS3Uploader serviceS3Uploader, ServiceSqsSender serviceSqsSender){
        this.serviceGeradorBoletos = serviceGeradorBoletos;
        this.interfaceJpaPropriedades = interfaceJpaPropriedades;
        this.serviceS3Uploader = serviceS3Uploader;
        this.serviceSqsSender = serviceSqsSender;
        this.propriedades = new ArrayList<>();
        carregarPropriedades();
    }
    
    public List<UnidadeResidencial> obterUnidadesResidenciais() {
        List<UnidadeResidencial> unidadesResidenciais = new ArrayList<>();
        for (Propriedade propriedade : propriedades) {
            if(propriedade instanceof UnidadeResidencial){
                unidadesResidenciais.add((UnidadeResidencial)propriedade);
            }
        }
        return unidadesResidenciais;
    }

    public int obterQtdResidencias(){
        return obterUnidadesResidenciais().size();
    }

    public boolean validaProprietarioTerrenoPorCpf(String cpf, Propriedade propriedade){
        for (Propriedade prop: propriedades){
            if(propriedade.getProprietario().getCpf().equals(cpf) && prop.equals(propriedade)) return true;
        }
        return false;
    }
   
    public List<Propriedade> obterTodasPropriedades() {
        return this.propriedades;
    }

    public void carregarPropriedades() {
        this.propriedades.addAll(this.interfaceJpaPropriedades.findAll());
    }

    public void gerarPdfBoletosTodasProps(double valorMulta, double porcentagemMensalJuros, LocalDateTime dataVencimento, double taxaBase, double contaAgua,Condominio condominio) throws Exception {
        var qtd = obterQtdResidencias();
        for (Propriedade propriedade: propriedades){
           var boleto = new BoletoCondominial(valorMulta, porcentagemMensalJuros, dataVencimento, taxaBase, contaAgua, propriedade, condominio);
           boleto.calcularTaxaFinal(qtd);
           String caminhoBoleto = this.serviceGeradorBoletos.gerarBoleto(boleto);
           String s3filePath = this.serviceS3Uploader.uploadFile(caminhoBoleto, boleto);
           this.serviceSqsSender.sendMessage(propriedade.getProprietario().getEmail(), "Boleto Condominial", s3filePath);
        }
    }

}
