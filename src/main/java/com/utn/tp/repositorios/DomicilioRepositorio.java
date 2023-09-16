package com.utn.tp.repositorios;

import com.utn.tp.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepositorio extends JpaRepository<Domicilio, Long>{
}
