package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import java.util.List;

public interface ActivoServiceInterface {
    public List<Activo> listarTodo();
    public void guardar(Activo activo);
    public void eliminar(Activo activo);
    public Activo encontrarPorNumeroActivo(ActivoPK activoPK);
}
