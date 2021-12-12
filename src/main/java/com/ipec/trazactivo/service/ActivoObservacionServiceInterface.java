package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.ActivoObservacion;
import com.ipec.trazactivo.model.ActivoPK;
import java.util.List;

public interface ActivoObservacionServiceInterface {
    public List<ActivoObservacion> listarTodo();
    public void guardar(ActivoObservacion activoObservacion);
    public void eliminar(ActivoObservacion activoObservacion);
    public List<ActivoObservacion> encontrarPorNumeroActivo(ActivoPK activoPK);
    public ActivoObservacion encontrarPorId(Integer idActivoObservacion);
}
