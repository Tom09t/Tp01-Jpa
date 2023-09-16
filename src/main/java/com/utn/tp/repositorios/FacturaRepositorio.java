package com.utn.tp.repositorios;

import com.utn.tp.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Long>{
}
