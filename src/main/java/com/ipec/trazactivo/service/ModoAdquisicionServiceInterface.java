package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.ModoAdquisicion;
import java.util.List;

public interface ModoAdquisicionServiceInterface {
    public List<ModoAdquisicion> listarTodo();
    public void guardar(ModoAdquisicion modoAdquisicion);
    public void eliminar(ModoAdquisicion modoAdquisicion);
    public ModoAdquisicion encontrarPorId(ModoAdquisicion modoAdquisicion);
}
