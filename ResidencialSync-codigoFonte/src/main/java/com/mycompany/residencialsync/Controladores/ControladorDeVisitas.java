/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Controladores;

import com.mycompany.residencialsync.Catalogos.CatalogoVisitas;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import com.mycompany.residencialsync.Model.Visita;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author rafay
 */
@Component
public class ControladorDeVisitas {
    private CatalogoVisitas catalogoVisita; 

    public ControladorDeVisitas(CatalogoVisitas catalogoVisita) {

        this.catalogoVisita = catalogoVisita;
    }
    
    public String agendarVisita(
            String cpf, String placaCarro, LocalDateTime dataHora1, UnidadeResidencial unidade
    ){
        return this.catalogoVisita.registrarVisita(cpf, placaCarro, dataHora1, unidade);
    }

    
    public CatalogoVisitas getCatalogoVisita() {
        return catalogoVisita;
    }

    public void setCatalogoVisita(CatalogoVisitas catalogoVisita) {
        this.catalogoVisita = catalogoVisita;
    }
    
    
}
