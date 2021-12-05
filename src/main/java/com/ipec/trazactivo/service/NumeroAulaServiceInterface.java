package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.NumeroAula;
import java.util.List;

public interface NumeroAulaServiceInterface {
    public List<NumeroAula> listarTodo();
    public void guardar(NumeroAula numeroAula);
    public void eliminar(NumeroAula numeroAula);
    public NumeroAula encontrarPorId(Integer idNumeroAula);
}
