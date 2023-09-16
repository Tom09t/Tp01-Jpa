package com.utn.tp.repositorios;

import com.utn.tp.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepositorio extends JpaRepository<Rubro, Long>{
}
