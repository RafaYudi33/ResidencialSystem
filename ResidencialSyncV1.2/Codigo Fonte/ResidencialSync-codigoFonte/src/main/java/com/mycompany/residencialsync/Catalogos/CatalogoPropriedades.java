/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaPropriedades;
import com.mycompany.residencialsync.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafay
 */

@SuppressWarnings("FieldCanBeLocal")
@Component
public class CatalogoPropriedades {
    private List<Propriedade> propriedades;
    private final InterfaceJpaPropriedades interfaceJpaPropriedades;

    @Autowired
    public CatalogoPropriedades(InterfaceJpaPropriedades interfaceJpaPropriedades){
        this.interfaceJpaPropriedades = interfaceJpaPropriedades;
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

    public void gerarPdfBoletosTodasProps(double valorMulta, double porcentagemMensalJuros, LocalDateTime dataVencimento, double taxaBase, double contaAgua,Condominio condominio) {
        var qtd = obterQtdResidencias();
       for (Propriedade propriedade: propriedades){
           var boleto = new BoletoCondominial(valorMulta, porcentagemMensalJuros, dataVencimento, taxaBase, contaAgua, propriedade, condominio);
           boleto.calcularTaxaFinal(qtd);
           boleto.gerarPDF();
       }

    }
}
