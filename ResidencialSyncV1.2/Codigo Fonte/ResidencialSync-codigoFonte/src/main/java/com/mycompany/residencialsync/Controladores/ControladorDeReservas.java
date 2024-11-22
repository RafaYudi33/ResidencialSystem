/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Controladores;

import com.mycompany.residencialsync.Catalogos.CatalogoDeAreasDeLazer;
import com.mycompany.residencialsync.Catalogos.CatalogoMorador;
import com.mycompany.residencialsync.Catalogos.CatalogoPropriedades;
import com.mycompany.residencialsync.Catalogos.CatalogoReservas;
import com.mycompany.residencialsync.Model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafay
 */
@Component
public class ControladorDeReservas {
   
    private CatalogoDeAreasDeLazer catalogoAreasDeLazer;
    private CatalogoMorador catalogoMoradores;
    private CatalogoReservas catalogoReservas;

    private CatalogoPropriedades catalogoPropriedades;

    public ControladorDeReservas(
            CatalogoDeAreasDeLazer catalogoAreasDeLazer, CatalogoMorador catalogoMoradores,
            CatalogoReservas catalogoReservas, CatalogoPropriedades catalogoPropriedades
    ) {
        this.catalogoAreasDeLazer = catalogoAreasDeLazer;
        this.catalogoMoradores = catalogoMoradores;
        this.catalogoReservas = catalogoReservas;
        this.catalogoPropriedades = catalogoPropriedades;
    }

    public Reserva realizarReserva(String cpf, AreaDeLazer areaDeLazer, LocalDateTime dataEhora, int duracao, Propriedade propriedade) {
       return this.catalogoReservas.realizarReserva(
               cpf, areaDeLazer, dataEhora, duracao, propriedade, this.catalogoMoradores, this.catalogoPropriedades
       );
    }

    
    public List<AreaDeLazer> obterAreasDeLazer() {
        return this.catalogoAreasDeLazer.listarTodasAreasLazer();
    }

    public List<Propriedade> listarPropriedades(){
        return this.catalogoPropriedades.obterTodasPropriedades();
    }
    public List<AreaDeLazer> listarAreasDeLazerDisponiveis(LocalDateTime dataHora) {
        var areasOcupadas = this.catalogoReservas.buscaAreasOcupadas(dataHora);
        return this.catalogoAreasDeLazer.listarAreasDisponiveis(areasOcupadas);
    }

   
    public void gerarComprovantePDF(Reserva reserva) {
        this.catalogoReservas.gerarComprovantePDF(reserva);
    }
   
   
    public CatalogoDeAreasDeLazer getCatalogoAreasDeLazer() {
        return catalogoAreasDeLazer;
    }

    public void setCatalogoAreasDeLazer(CatalogoDeAreasDeLazer catalogoAreasDeLazer) {
        this.catalogoAreasDeLazer = catalogoAreasDeLazer;
    }
    
}
