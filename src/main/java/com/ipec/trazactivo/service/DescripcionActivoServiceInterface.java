package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.DescripcionActivo;
import java.util.List;

public interface DescripcionActivoServiceInterface {
    public List<DescripcionActivo> listarTodo();
    public void guardar(DescripcionActivo descripcionActivo);
    public void eliminar(DescripcionActivo descripcionActivo);
    public DescripcionActivo encontrarPorId(Integer idDescripcionActivo);
}
