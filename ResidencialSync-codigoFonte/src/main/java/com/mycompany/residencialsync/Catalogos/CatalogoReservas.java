/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;

import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaReserva;
import com.mycompany.residencialsync.Model.*;
import com.mycompany.residencialsync.ServicosExternos.ServiceGeradorComprovante;
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
public class CatalogoReservas {
    private List<Reserva> reservas;
    private final InterfaceJpaReserva interfaceJpaReserva;
    private final ServiceGeradorComprovante serviceGeradorComprovante;

    @Autowired
    public CatalogoReservas(InterfaceJpaReserva interfaceJpaReserva, ServiceGeradorComprovante serviceGeradorComprovante) {
        this.interfaceJpaReserva = interfaceJpaReserva;
        this.serviceGeradorComprovante = serviceGeradorComprovante;
        this.reservas = new ArrayList<>();
        carregarReservas();
    }
    
    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public boolean verificaDisponibilidade(LocalDateTime dataHora, AreaDeLazer area) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.getDataEhora().equals(dataHora) && reserva.getAreaDeLazer().equals(area)){
                return false;
            };
        }
        return true;
    }

    public Reserva realizarReserva(
            String cpf, AreaDeLazer areaDeLazer, LocalDateTime dataEhora, int duracao, Propriedade propriedade,
            CatalogoMorador catalogoMoradores,
            CatalogoPropriedades catalogoPropriedades
    ) {
        if (!this.verificaDisponibilidade(dataEhora, areaDeLazer)) {
            throw new RuntimeException("Área de lazer indisponível");
        }

        boolean validado = false;
        if (propriedade instanceof UnidadeResidencial) {
            validado = catalogoMoradores.validaMoradorPorCPF(cpf, propriedade);
            System.out.println(validado);
        } else if (propriedade instanceof Terreno) {
            validado = catalogoPropriedades.validaProprietarioTerrenoPorCpf(cpf, propriedade);
        }

        if (validado) {
            var reserva = new Reserva(dataEhora, duracao, areaDeLazer, propriedade);
            this.addReserva(reserva);
            propriedade.addReserva(reserva);
            return reserva;

        } else {
            throw new RuntimeException("Morador/Proprietário de terreno inválido!");
        }
    }

    public void gerarComprovantePDF(Reserva reserva) {
       this.serviceGeradorComprovante.gerarComprovante(reserva);
    }
    
    public List<AreaDeLazer> buscaAreasOcupadas(LocalDateTime dataEhora) {
        List<AreaDeLazer> areasOcupadas = new ArrayList<>();

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            var duracao = reserva.getDuracaoReserva();
            var horarioInicial = reserva.getDataEhora();
            var horarioFinal = horarioInicial.plusHours(duracao);

            if(!dataEhora.isBefore(horarioInicial) && dataEhora.isBefore(horarioFinal)){
                areasOcupadas.add(reserva.getAreaDeLazer());
            }
        }
        return areasOcupadas;
    } 
    
    public void carregarReservas(){
        this.reservas.addAll(this.interfaceJpaReserva.findAll());
    }
    public void salvarDadosFechamentoSistema(){
        for (Reserva reserva : reservas){
            if(reserva.getId() == null){
                this.interfaceJpaReserva.save(reserva);
            }
        }
    }
}
