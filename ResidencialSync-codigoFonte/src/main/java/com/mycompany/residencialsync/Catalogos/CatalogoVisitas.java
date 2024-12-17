/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;
import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaVisita;
import com.mycompany.residencialsync.Model.UnidadeResidencial;
import com.mycompany.residencialsync.Model.Visita;
import com.mycompany.residencialsync.ServicosExternos.ServiceGeradorQrCode;
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

@Component
@DependsOn("flyway")
public class CatalogoVisitas {
    private List<Visita> visitas;
    private final ServiceGeradorQrCode serviceGeradorQrCode;
    private final InterfaceJpaVisita interfaceJpaVisita;

    @Autowired
    public CatalogoVisitas(ServiceGeradorQrCode serviceGeradorQrCode, InterfaceJpaVisita interfaceJpaVisita){
        this.serviceGeradorQrCode = serviceGeradorQrCode;
        this.interfaceJpaVisita = interfaceJpaVisita;
        this.visitas = new ArrayList<>();
        carregarVisitas();
    }
    
    public String registrarVisita(String cpf, String placaCarro, LocalDateTime dataHora1, UnidadeResidencial unidade) {
        var visita = new Visita(dataHora1, placaCarro, cpf, unidade);
        visitas.add(visita);
        return this.serviceGeradorQrCode.gerarQrCode(visita);
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
