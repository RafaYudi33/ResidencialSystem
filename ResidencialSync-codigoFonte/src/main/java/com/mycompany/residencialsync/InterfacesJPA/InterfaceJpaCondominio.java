package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterfaceJpaCondominio extends JpaRepository<Condominio, Long> {

    @Query("SELECT c FROM Condominio c ORDER BY c.id ASC")
    Optional<Condominio> findFirstCondominio();

}
