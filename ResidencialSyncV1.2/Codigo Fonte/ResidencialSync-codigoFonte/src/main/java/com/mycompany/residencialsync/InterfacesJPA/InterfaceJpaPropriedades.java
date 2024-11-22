package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceJpaPropriedades extends JpaRepository<Propriedade, Long> {
}
