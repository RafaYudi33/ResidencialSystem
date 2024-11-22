/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import com.mycompany.residencialsync.Catalogos.CatalogoCondominio;
import com.mycompany.residencialsync.Catalogos.CatalogoDeAreasDeLazer;
import com.mycompany.residencialsync.Catalogos.CatalogoMorador;
import com.mycompany.residencialsync.Catalogos.CatalogoPropriedades;
import com.mycompany.residencialsync.Catalogos.CatalogoReservas;
import com.mycompany.residencialsync.Catalogos.CatalogoVisitas;
import com.mycompany.residencialsync.Controladores.ControladorGeradorBoletos;
import com.mycompany.residencialsync.Controladores.ControladorDeReservas;
import com.mycompany.residencialsync.Controladores.ControladorDeVisitas;
import com.mycompany.residencialsync.Controladores.ControladorMoradorResidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafay
 */
@Component
public class SistemaCondominio {
    private CatalogoCondominio catalogoCondominios;
    private CatalogoDeAreasDeLazer catalogoAreasDeLazer;
    private CatalogoMorador catalogoMoradores;
    private CatalogoPropriedades catalogoPropriedades;
    private CatalogoReservas catalogoReservas;
    private CatalogoVisitas catalogoVisitas;
    private ControladorGeradorBoletos controladorBoletosEtaxas;
    private ControladorDeReservas controladorReservas;
    private ControladorDeVisitas controladorVisitas;
    private ControladorMoradorResidencia controladorMoradorResidencia;


    @Autowired
    public SistemaCondominio(
            CatalogoCondominio catalogoCondominios,
            CatalogoDeAreasDeLazer catalogoAreasDeLazer,
            CatalogoMorador catalogoMoradores,
            CatalogoPropriedades catalogoPropriedades,
            CatalogoReservas catalogoReservas,
            CatalogoVisitas catalogoVisitas,
            ControladorGeradorBoletos controladorBoletosEtaxas,
            ControladorDeReservas controladorReservas,
            ControladorDeVisitas controladorVisitas,
            ControladorMoradorResidencia controladorMoradorResidencia
    ) {
        this.catalogoCondominios = catalogoCondominios;
        this.catalogoAreasDeLazer = catalogoAreasDeLazer;
        this.catalogoMoradores = catalogoMoradores;
        this.catalogoPropriedades = catalogoPropriedades;
        this.catalogoReservas = catalogoReservas;
        this.catalogoVisitas = catalogoVisitas;

        this.controladorBoletosEtaxas = controladorBoletosEtaxas;
        this.controladorReservas = controladorReservas;
        this.controladorVisitas = controladorVisitas;
        this.controladorMoradorResidencia = controladorMoradorResidencia;
    }
    
    public CatalogoCondominio getCatalogoCondominios() {
        return catalogoCondominios;
    }

    public void setCatalogoCondominios(CatalogoCondominio catalogoCondominios) {
        this.catalogoCondominios = catalogoCondominios;
    }

    public CatalogoDeAreasDeLazer getCatalogoAreasDeLazer() {
        return catalogoAreasDeLazer;
    }

    public void setCatalogoAreasDeLazer(CatalogoDeAreasDeLazer catalogoAreasDeLazer) {
        this.catalogoAreasDeLazer = catalogoAreasDeLazer;
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

    public CatalogoReservas getCatalogoReservas() {
        return catalogoReservas;
    }

    public void setCatalogoReservas(CatalogoReservas catalogoReservas) {
        this.catalogoReservas = catalogoReservas;
    }

    public CatalogoVisitas getCatalogoVisitas() {
        return catalogoVisitas;
    }

    public void setCatalogoVisitas(CatalogoVisitas catalogoVisitas) {
        this.catalogoVisitas = catalogoVisitas;
    }

    public ControladorGeradorBoletos getControladorBoletosEtaxas() {
        return controladorBoletosEtaxas;
    }

    public void setControladorBoletosEtaxas(ControladorGeradorBoletos controladorBoletosEtaxas) {
        this.controladorBoletosEtaxas = controladorBoletosEtaxas;
    }

    public ControladorDeReservas getControladorReservas() {
        return controladorReservas;
    }

    public void setControladorReservas(ControladorDeReservas controladorReservas) {
        this.controladorReservas = controladorReservas;
    }

    public ControladorDeVisitas getControladorVisitas() {
        return controladorVisitas;
    }

    public void setControladorVisitas(ControladorDeVisitas controladorVisitas) {
        this.controladorVisitas = controladorVisitas;
    }

    public ControladorMoradorResidencia getControladorMoradorResidencia() {
        return controladorMoradorResidencia;
    }

    public void setControladorMoradorResidencia(ControladorMoradorResidencia controladorMoradorResidencia) {
        this.controladorMoradorResidencia = controladorMoradorResidencia;
    }

    public void fechamentoSistema(){
        this.catalogoReservas.salvarDadosFechamentoSistema();
        this.catalogoVisitas.salvarDadosFechamentoSistema();
    }
    
    
    
    
    
    
}
