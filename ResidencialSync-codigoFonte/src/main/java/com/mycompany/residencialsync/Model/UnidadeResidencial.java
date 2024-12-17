/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rafay
 */

@Entity
public class UnidadeResidencial extends Propriedade {

    @OneToMany(mappedBy = "residencia", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Morador> moradores;

    public UnidadeResidencial(List<Morador> moradores) {
        this.moradores = moradores;
    }

    public UnidadeResidencial(){
        super();
        this.moradores = new ArrayList<>();
    }
    
    public UnidadeResidencial(String logradouro, String cep, String cidade, String bairro, String uf, 
            int numero, int tamanho, List<Morador> moradores, Proprietario proprietario){
        
        super(logradouro, cep, cidade, bairro, uf, numero, tamanho, proprietario);
        this.moradores = moradores;
    }



    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }

    public Long getId(){
        return super.getId();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UnidadeResidencial that)) return false;
        return Objects.equals(getMoradores(), that.getMoradores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoradores());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
