/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Controladores;

import com.mycompany.residencialsync.Catalogos.CatalogoCondominio;
import com.mycompany.residencialsync.Catalogos.CatalogoMorador;
import com.mycompany.residencialsync.Catalogos.CatalogoPropriedades;
import com.mycompany.residencialsync.Model.BoletoCondominial;
import com.mycompany.residencialsync.Model.Condominio;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rafay
 */
@Component
public class ControladorGeradorBoletos {
    private CatalogoCondominio catalogoCondominios;
    private CatalogoMorador catalogoMoradores;
    private CatalogoPropriedades catalogoPropriedades;

    public ControladorGeradorBoletos(
            CatalogoCondominio catalogoCondominios, CatalogoMorador catalogoMoradores, 
            CatalogoPropriedades catalogoPropriedades
    ) {
        this.catalogoCondominios = catalogoCondominios;
        this.catalogoMoradores = catalogoMoradores;
        this.catalogoPropriedades = catalogoPropriedades;
    }

    public void gerarPDFBoletos(double valotMulta, double porcentagemMensalJuros, LocalDateTime dataVencimento, double taxaBase, double contaAgua) throws IOException {
        this.catalogoPropriedades.gerarPdfBoletosTodasProps(valotMulta, porcentagemMensalJuros, dataVencimento, taxaBase, contaAgua, this.catalogoCondominios.recuperarCondominio());
    }

    public CatalogoCondominio getCatalogoCondominios() {
        return catalogoCondominios;
    }

    public void setCatalogoCondominios(CatalogoCondominio catalogoCondominios) {
        this.catalogoCondominios = catalogoCondominios;
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
