package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.ActivoObservacion;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.repository.ActivoObservacionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivoObservacionServiceImpl implements ActivoObservacionServiceInterface {

    @Autowired
    private ActivoObservacionDao activoObservacionDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<ActivoObservacion> listarTodo() {
        return (List<ActivoObservacion>) activoObservacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(ActivoObservacion activoObservacion) {
        activoObservacionDao.save(activoObservacion);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(ActivoObservacion activoObservacion) {
        activoObservacionDao.delete(activoObservacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivoObservacion> encontrarPorNumeroActivo(ActivoPK activoPK) {
        return activoObservacionDao.findByActivoPK(activoPK);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ActivoObservacion encontrarPorId(Integer idActivoObservacion) {
        return activoObservacionDao.findById(idActivoObservacion).orElse(null);
    }
}
