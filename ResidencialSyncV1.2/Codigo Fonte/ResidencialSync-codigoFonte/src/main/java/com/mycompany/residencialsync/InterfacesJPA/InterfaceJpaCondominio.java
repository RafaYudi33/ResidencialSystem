package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceJpaCondominio extends JpaRepository<Condominio, Long> {
}
