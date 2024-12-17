/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.Entity;

import java.util.Objects;

/**
 *
 * @author rafay
 */

@Entity
public class Terreno extends Propriedade {
    private boolean estaEmConstrucao;
    
    public Terreno(){
        super();
    }
    
    public Terreno(String logradouro, String cep, String cidade, String bairro, 
            String uf, int numero, int tamanho, boolean estaEmConstrucao,
            Proprietario proprietario) 
    {
        super(logradouro, cep, cidade, bairro, uf, numero, tamanho, proprietario);
        this.estaEmConstrucao = estaEmConstrucao;
    }

    public boolean isEstaEmConstrucao() {
        return estaEmConstrucao;
    }

    public void setEstaEmConstrucao(boolean estaEmConstrucao) {
        this.estaEmConstrucao = estaEmConstrucao;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Terreno terreno)) return false;
        return isEstaEmConstrucao() == terreno.isEstaEmConstrucao();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEstaEmConstrucao());
    }
}
