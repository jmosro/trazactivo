package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.TipoAnotacion;
import java.util.List;

public interface TipoAnotacionServiceInterface {
    public List<TipoAnotacion> listarTodo();
    public void guardar(TipoAnotacion tipoAnotacion);
    public void eliminar(TipoAnotacion tipoAnotacion);
    public TipoAnotacion encontrarPorId(Integer idTipoAnotacion);
}