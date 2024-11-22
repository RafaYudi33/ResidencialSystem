/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaVisita;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import com.mycompany.residencialsync.Model.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafay
 */

@SuppressWarnings("ALL")
@Component
public class CatalogoVisitas {
    private List<Visita> visitas;
    private final InterfaceJpaVisita interfaceJpaVisita;

    @Autowired
    public CatalogoVisitas(InterfaceJpaVisita interfaceJpaVisita){
        this.interfaceJpaVisita = interfaceJpaVisita;
        this.visitas = new ArrayList<>();
        carregarVisitas();
    }
    
    public String registrarVisita(String cpf, String placaCarro, LocalDateTime dataHora1, UnidadeResidencial unidade) {
        var visita = new Visita(dataHora1, placaCarro, cpf, unidade);
        visitas.add(visita);
        return visita.gerarQrCode();
    }
    
    public void carregarVisitas(){
        this.visitas.addAll(this.interfaceJpaVisita.findAll());
    }

    public void salvarDadosFechamentoSistema(){
        for (Visita visita : visitas){
            if(visita.getId() == null){
                this.interfaceJpaVisita.save(visita);
            }
        }
    }



}
