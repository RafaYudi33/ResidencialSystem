/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rafay
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logradouro;
    private String cep;
    private String cidade;
    private String bairro;
    private String uf;
    private int numero;
    private int tamanho;

    @ManyToOne
    @JoinColumn(name = "proprietario_id", foreignKey = @ForeignKey(name = "FK_proprietario_propriedade"))
    private Proprietario proprietario;

    @OneToMany(mappedBy = "propriedade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reserva> reservas;
    
    public Propriedade(){
    
    }
    
    public Propriedade(
            String logradouro, String cep, String cidade, 
            String bairro, String uf, int numero, int tamanho,
            Proprietario proprietario
    ){
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.numero = numero;
        this.tamanho = tamanho;
        this.proprietario = proprietario;
    }


    public int getQtdReservasUltimos30dias(){
        var dataAtual = LocalDateTime.now();
        var dataLimite = dataAtual.minusDays(30);
        List<Reserva> reservas30dias = new ArrayList<>();

        for (Reserva reserva : reservas) {
            if (reserva.getDataEhora().isAfter(dataLimite) && reserva.getDataEhora().isBefore(dataAtual)) {
                reservas30dias.add(reserva);
            }
        }
        System.out.println(reservas30dias.size());
        return reservas30dias.size();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    public List<Reserva> getReservas(){
        return this.reservas;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Logradouro: "+ logradouro + "; Nº " + numero + "; CEP: "+ cep;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Propriedade that)) return false;
        return getNumero() == that.getNumero() && getTamanho() == that.getTamanho() && Objects.equals(getId(), that.getId()) && Objects.equals(getLogradouro(), that.getLogradouro()) && Objects.equals(getCep(), that.getCep()) && Objects.equals(getCidade(), that.getCidade()) && Objects.equals(getBairro(), that.getBairro()) && Objects.equals(getUf(), that.getUf()) && Objects.equals(getProprietario(), that.getProprietario()) && Objects.equals(getReservas(), that.getReservas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogradouro(), getCep(), getCidade(), getBairro(), getUf(), getNumero(), getTamanho(), getProprietario(), getReservas());
    }

    public Long getId() {
        return this.id;
    }
}
