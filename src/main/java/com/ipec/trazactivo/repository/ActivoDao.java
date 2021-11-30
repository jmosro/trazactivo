package com.ipec.trazactivo.repository;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivoDao extends JpaRepository<Activo, ActivoPK>{
    
}
