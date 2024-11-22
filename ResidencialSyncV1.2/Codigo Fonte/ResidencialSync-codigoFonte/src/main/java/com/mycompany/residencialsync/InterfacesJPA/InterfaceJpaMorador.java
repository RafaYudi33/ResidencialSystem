package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceJpaMorador extends JpaRepository<Morador, Long> {
}
