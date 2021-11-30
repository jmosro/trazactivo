package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.repository.ActivoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivoServiceImpl implements ActivoServiceInterface {

    @Autowired
    private ActivoDao activoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Activo> listarTodo() {
        return (List<Activo>) activoDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(Activo activo) {
        activoDao.save(activo);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Activo activo) {
        activoDao.delete(activo);
    }

    @Override
    @Transactional(readOnly = true)
    public Activo encontrarPorNumeroActivo(ActivoPK activoPK) {
        return activoDao.findById(activoPK).orElse(null);
    }
    
}
