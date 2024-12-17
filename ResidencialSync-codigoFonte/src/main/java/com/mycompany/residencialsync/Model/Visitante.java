/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 *
 * @author rafay
 */
@Entity
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placaVeiculo;
    private String cpf; 

    public Visitante() {
    }
    
    public Visitante(String placaVeiculo, String cpf) {
        this.placaVeiculo = placaVeiculo;
        this.cpf = cpf;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Visitante{" + "placaVeiculo=" + placaVeiculo + ", cpf=" + cpf + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Visitante visitante)) return false;
        return Objects.equals(id, visitante.id) && Objects.equals(getPlacaVeiculo(), visitante.getPlacaVeiculo()) && Objects.equals(getCpf(), visitante.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getPlacaVeiculo(), getCpf());
    }
}
