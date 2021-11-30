package com.ipec.trazactivo.repository;

import com.ipec.trazactivo.model.EstadoActivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoActivoDao extends JpaRepository<EstadoActivo, Integer> {
    
}
