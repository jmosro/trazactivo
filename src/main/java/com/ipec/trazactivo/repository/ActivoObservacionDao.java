package com.ipec.trazactivo.repository;

import com.ipec.trazactivo.model.ActivoObservacion;
import com.ipec.trazactivo.model.ActivoPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivoObservacionDao extends JpaRepository<ActivoObservacion, Integer>{
    List<ActivoObservacion> findByActivoPK(ActivoPK activoPK);
    
}
