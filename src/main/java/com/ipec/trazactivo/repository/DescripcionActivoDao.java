package com.ipec.trazactivo.repository;

import com.ipec.trazactivo.model.DescripcionActivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescripcionActivoDao extends JpaRepository<DescripcionActivo, Integer> {
    
}
