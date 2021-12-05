package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.DescripcionActivo;
import com.ipec.trazactivo.repository.DescripcionActivoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DescripcionActivoServiceImpl implements DescripcionActivoServiceInterface {

    @Autowired
    private DescripcionActivoDao descripcionActivoDao;
            
    @Override
    @Transactional(readOnly = true)
    public List<DescripcionActivo> listarTodo() {
        return (List<DescripcionActivo>) descripcionActivoDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(DescripcionActivo descripcionActivo) {
        descripcionActivoDao.save(descripcionActivo);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(DescripcionActivo descripcionActivo) {
        descripcionActivoDao.delete(descripcionActivo);
    }

    @Override
    @Transactional(readOnly = true)
    public DescripcionActivo encontrarPorId(Integer idDescripcionActivo) {
        return descripcionActivoDao.findById(idDescripcionActivo).orElse(null);
    }
    
}
