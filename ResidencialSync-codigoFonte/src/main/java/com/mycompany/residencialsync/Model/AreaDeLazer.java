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
public class AreaDeLazer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private float tamanho;
    private String tipo;


    public AreaDeLazer(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public AreaDeLazer(String nome, float tamanho, String tipo) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "; Tamanho: " + tamanho + "; Tipo: " + tipo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AreaDeLazer that)) return false;
        return Float.compare(getTamanho(), that.getTamanho()) == 0 && Objects.equals(getNome(), that.getNome()) && Objects.equals(getTipo(), that.getTipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getTamanho(), getTipo());
    }
}
