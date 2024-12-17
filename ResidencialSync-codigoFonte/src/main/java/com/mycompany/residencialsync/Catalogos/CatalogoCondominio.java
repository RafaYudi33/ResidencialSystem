/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;
import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaCondominio;
import com.mycompany.residencialsync.Model.Condominio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafay
 */
@Component
@DependsOn("flyway")
public class CatalogoCondominio {
    private Condominio condominio;
    private final InterfaceJpaCondominio interfaceJpaCondominio;

    @Autowired
    public CatalogoCondominio(InterfaceJpaCondominio interfaceJpaCondominio){
        this.interfaceJpaCondominio = interfaceJpaCondominio;
       carregarCondominio();
    }
    
    public void carregarCondominio(){
        this.interfaceJpaCondominio.findFirstCondominio().ifPresentOrElse(
                condominio -> this.condominio = condominio,
                () ->{
                    throw new RuntimeException("Condominio inexistente");
                }
        );
    }

    public Condominio recuperarCondominio() {
        return condominio;
    }

}
