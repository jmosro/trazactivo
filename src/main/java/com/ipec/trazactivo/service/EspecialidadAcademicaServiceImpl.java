package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.EspecialidadAcademica;
import com.ipec.trazactivo.repository.EspecialidadAcademicaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspecialidadAcademicaServiceImpl implements EspecialidadAcademicaServiceInterface {

    @Autowired
    private EspecialidadAcademicaDao especialidadAcademicaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadAcademica> listarTodo() {
        return (List<EspecialidadAcademica>) especialidadAcademicaDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(EspecialidadAcademica especialidadAcademica) {
        especialidadAcademicaDao.save(especialidadAcademica);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(EspecialidadAcademica especialidadAcademica) {
        especialidadAcademicaDao.delete(especialidadAcademica);
    }

    @Override
    @Transactional(readOnly = true)
    public EspecialidadAcademica encontrarPorId(EspecialidadAcademica especialidadAcademica) {
        return especialidadAcademicaDao.findById(especialidadAcademica.getIdEspecialidadAcademica()).orElse(null);
    }
    
}
