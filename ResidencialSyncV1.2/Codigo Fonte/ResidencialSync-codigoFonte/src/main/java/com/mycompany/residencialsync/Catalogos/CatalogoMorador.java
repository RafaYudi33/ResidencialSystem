/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaMorador;
import com.mycompany.residencialsync.Model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafay
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
@Component
public class CatalogoMorador {
    private List<Morador> moradores;
    private final InterfaceJpaMorador interfaceJpaMorador;

    public CatalogoMorador(InterfaceJpaMorador interfaceJpaMorador) {
        this.interfaceJpaMorador = interfaceJpaMorador;
        this.moradores = new ArrayList<>();
        carregarMoradores();
    }
    
    public Boolean validaMoradorPorCPF(String cpf, Propriedade propriedade) {
        for (Morador morador: moradores){

            var idResidenciaMorador = morador.getResidencia().getId();
            if(morador.getCpf().equals(cpf) && idResidenciaMorador.equals(propriedade.getId())){
                return true;
            }
        }
        return false;
    }


    public int getTotalMoradores() {
        return this.moradores.size();
    }

 
    public Morador buscaMoradorPorVeiculo(String placa) {
        for (Morador morador : moradores) {
            var veiculos = morador.getVeiculos();
            for (Veiculo veiculo : veiculos) {
                if(veiculo.getPlaca().equals(placa)){
                    
                    return morador;
                }
            }  
        }
        return null; 
    }
    
    public void carregarMoradores(){
        this.moradores.addAll(this.interfaceJpaMorador.findAll());
    }
    
}
