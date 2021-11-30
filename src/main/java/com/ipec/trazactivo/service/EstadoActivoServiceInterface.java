package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.EstadoActivo;
import java.util.List;

public interface EstadoActivoServiceInterface {
    public List<EstadoActivo> listarTodo();
    public void guardar(EstadoActivo estadoActivo);
    public void eliminar(EstadoActivo estadoActivo);
    public EstadoActivo encontrarPorId(EstadoActivo estadoActivo);
}
