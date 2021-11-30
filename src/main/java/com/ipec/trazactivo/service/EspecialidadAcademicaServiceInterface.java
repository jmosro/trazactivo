package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.EspecialidadAcademica;
import java.util.List;

public interface EspecialidadAcademicaServiceInterface {
    public List<EspecialidadAcademica> listarTodo();
    public void guardar(EspecialidadAcademica especialidadAcademica);
    public void eliminar(EspecialidadAcademica especialidadAcademica);
    public EspecialidadAcademica encontrarPorId(EspecialidadAcademica especialidadAcademica);
}
