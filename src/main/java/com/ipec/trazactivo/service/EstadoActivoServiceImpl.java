package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.EstadoActivo;
import com.ipec.trazactivo.repository.EstadoActivoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoActivoServiceImpl implements EstadoActivoServiceInterface {

    @Autowired
    private EstadoActivoDao estadoActivoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<EstadoActivo> listarTodo() {
        return (List<EstadoActivo>) estadoActivoDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(EstadoActivo estadoActivo) {
        estadoActivoDao.save(estadoActivo);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(EstadoActivo estadoActivo) {
        estadoActivoDao.delete(estadoActivo);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoActivo encontrarPorId(Integer idEstadoActivo) {
        return estadoActivoDao.findById(idEstadoActivo).orElse(null);
    }
    
}
