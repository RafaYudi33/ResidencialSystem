/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaAreaDeLazer;
import com.mycompany.residencialsync.Model.AreaDeLazer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rafay
 */

@Component
public class CatalogoDeAreasDeLazer {
    private List<AreaDeLazer> areasDeLazer;
    private final InterfaceJpaAreaDeLazer interfaceJpaAreaDeLazer;

    @Autowired
    public CatalogoDeAreasDeLazer(InterfaceJpaAreaDeLazer interfaceJpaAreaDeLazer) {
        this.interfaceJpaAreaDeLazer = interfaceJpaAreaDeLazer;
        this.areasDeLazer = new ArrayList<>();
        carregarAreasDeLazer();
    }

    public List<AreaDeLazer> listarTodasAreasLazer() {
        return areasDeLazer;
    }


    public List<AreaDeLazer> listarAreasDisponiveis(List<AreaDeLazer> listaDeAreasOcupadas) {
        List<AreaDeLazer> areasDisponiveis = new ArrayList<>();
        for (AreaDeLazer area : areasDeLazer) {
            if (!listaDeAreasOcupadas.contains(area)) {
                areasDisponiveis.add(area);
            }
        }
        return areasDisponiveis;
    }


    public void carregarAreasDeLazer(){
        this.areasDeLazer.addAll(this.interfaceJpaAreaDeLazer.findAll());
    }
    
}
