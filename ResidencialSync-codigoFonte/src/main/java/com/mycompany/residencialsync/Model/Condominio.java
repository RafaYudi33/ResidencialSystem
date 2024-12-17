/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Objects;

/**
 *
 * @author rafay
 */

@Entity
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int agencia;
    private char digitoAgencia;
    private int numeroConvenio;
    private int numeroCarteira;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;
    private String cnpj;

    private char digitoContaCorrente;
    private int contaCorrente;

    private int nossoNumero;

    
    public Condominio(){}
    
    public Condominio(
            String nome, int agencia, char digitoAgencia, int numeroConvenio, int numeroCarteira,
            String logradouro, String cep, String bairro, String cidade, String uf, 
            String cnpj
    ){
        this.nome = nome;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.numeroConvenio = numeroConvenio;
        this.numeroCarteira = numeroCarteira;
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cnpj = cnpj;
    }

    public int getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(int contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public int getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(int nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public char getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(char digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public int getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(int numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public int getNumeroCarteira() {
        return numeroCarteira;
    }

    public void setNumeroCarteira(int numeroCarteira) {
        this.numeroCarteira = numeroCarteira;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }



    @Override
    public String toString() {
        return "Condominio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", agencia=" + agencia +
                ", digitoAgencia=" + digitoAgencia +
                ", numeroConvenio=" + numeroConvenio +
                ", numeroCarteira=" + numeroCarteira +
                ", logradouro='" + logradouro + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    public char getDigitoContaCorrente() {
        return digitoContaCorrente;
    }

    public void setDigitoContaCorrente(char digitoContaCorrente) {
        this.digitoContaCorrente = digitoContaCorrente;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Condominio that)) return false;
        return getAgencia() == that.getAgencia() && getDigitoAgencia() == that.getDigitoAgencia() && getNumeroConvenio() == that.getNumeroConvenio() && getNumeroCarteira() == that.getNumeroCarteira() && digitoContaCorrente == that.digitoContaCorrente && getContaCorrente() == that.getContaCorrente() && getNossoNumero() == that.getNossoNumero() && Objects.equals(id, that.id) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getLogradouro(), that.getLogradouro()) && Objects.equals(getCep(), that.getCep()) && Objects.equals(getBairro(), that.getBairro()) && Objects.equals(getCidade(), that.getCidade()) && Objects.equals(getUf(), that.getUf()) && Objects.equals(getCnpj(), that.getCnpj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getNome(), getAgencia(), getDigitoAgencia(), getNumeroConvenio(), getNumeroCarteira(), getLogradouro(), getCep(), getBairro(), getCidade(), getUf(), getCnpj(), digitoContaCorrente, getContaCorrente(), getNossoNumero());
    }
}
