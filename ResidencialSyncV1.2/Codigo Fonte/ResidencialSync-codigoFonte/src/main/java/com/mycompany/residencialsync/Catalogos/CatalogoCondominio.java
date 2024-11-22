/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.residencialsync.Catalogos;
import com.mycompany.residencialsync.InterfacesJPA.InterfaceJpaCondominio;
import com.mycompany.residencialsync.Model.Condominio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafay
 */
@SuppressWarnings("FieldCanBeLocal")
@Component
public class CatalogoCondominio {
    private Condominio condominio;
    private final InterfaceJpaCondominio interfaceJpaCondominio;

    @Autowired
    public CatalogoCondominio(InterfaceJpaCondominio interfaceJpaCondominio){
        this.interfaceJpaCondominio = interfaceJpaCondominio;
       carregarCondominio();
    }
    
    public void carregarCondominio(){
        this.interfaceJpaCondominio.findById(1L).ifPresentOrElse(
                condominio -> this.condominio = condominio,
                () ->{
                    throw new RuntimeException("Condominio não existe");
                }
        );
    }

    public Condominio recuperarCondominio() {
        return condominio;
    }

}
