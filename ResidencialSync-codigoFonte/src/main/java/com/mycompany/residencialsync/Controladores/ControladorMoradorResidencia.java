/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Controladores;

import com.mycompany.residencialsync.Catalogos.CatalogoMorador;
import com.mycompany.residencialsync.Catalogos.CatalogoPropriedades;
import com.mycompany.residencialsync.Model.Morador;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author rafay
 */

@Component
public class ControladorMoradorResidencia {
    private CatalogoMorador catalogoMoradores;
    private CatalogoPropriedades catalogoPropriedades;

    public ControladorMoradorResidencia(CatalogoMorador catalogoMoradores, CatalogoPropriedades catalogoPropriedades) {
        this.catalogoMoradores = catalogoMoradores;
        this.catalogoPropriedades = catalogoPropriedades;
    }

    
    public List<Morador> listarMoradoresDeUmaUnidade(UnidadeResidencial unidade) {
        return unidade.getMoradores();
    }
    
    public List<UnidadeResidencial> obterUnidadesResidenciais() {
       return this.catalogoPropriedades.obterUnidadesResidenciais();
    }
    
    public Morador listarMoradorPorVeiculo(String placa) {
       return this.catalogoMoradores.buscaMoradorPorVeiculo(placa);
    }
    
    public CatalogoMorador getCatalogoMoradores() {
        return catalogoMoradores;
    }

    public void setCatalogoMoradores(CatalogoMorador catalogoMoradores) {
        this.catalogoMoradores = catalogoMoradores;
    }

    public CatalogoPropriedades getCatalogoPropriedades() {
        return catalogoPropriedades;
    }

    public void setCatalogoPropriedades(CatalogoPropriedades catalogoPropriedades) {
        this.catalogoPropriedades = catalogoPropriedades;
    }
   
       
}
