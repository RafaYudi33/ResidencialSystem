package com.mycompany.residencialsync.InterfacesJPA;

import com.mycompany.residencialsync.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceJpaReserva extends JpaRepository<Reserva, Long> {

}
