/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rafay
 */

@Entity
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Veiculo> veiculos;

    @ManyToOne
    @JoinColumn(name = "unidade_residencial_id",foreignKey = @ForeignKey(name = "FK_residencia_morador"))
    private UnidadeResidencial residencia; 
    private String cpf;
    private String nome;
    private String rg;
    private String telefone;

    public Morador(){
        this.veiculos = new ArrayList<>();
    }
    
    public Morador(List<Veiculo> veiculos, UnidadeResidencial residencia, String cpf, String nome, String rg, String telefone) {
        this.veiculos = veiculos;
        this.residencia = residencia;
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
    }
    
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public UnidadeResidencial getResidencia() {
        return residencia;
    }

    public void setResidencia(UnidadeResidencial residencia) {
        this.residencia = residencia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "\nMorador{" +
                "\nveiculos=" + veiculos +
                "\n, residenciaEndereço=" + (residencia != null ? "Rua: " + residencia.getLogradouro() + "|Numero: " + residencia.getNumero() : "null") +
                "\n, cpf=" + cpf +
                "\n, nome=" + nome +
                "\n, rg=" + rg +
                "\n, telefone=" + telefone +
                "}\n";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Morador morador)) return false;
        return Objects.equals(getVeiculos(), morador.getVeiculos()) && Objects.equals(getResidencia(), morador.getResidencia()) && Objects.equals(getCpf(), morador.getCpf()) && Objects.equals(getNome(), morador.getNome()) && Objects.equals(getRg(), morador.getRg()) && Objects.equals(getTelefone(), morador.getTelefone());
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVeiculos(), getResidencia(), getCpf(), getNome(), getRg(), getTelefone());
    }
}
