package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.AreaDeLazer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceJpaAreaDeLazer extends JpaRepository<AreaDeLazer, Long> {

}
