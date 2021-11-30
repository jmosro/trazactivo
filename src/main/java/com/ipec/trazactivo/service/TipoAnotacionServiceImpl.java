package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.TipoAnotacion;
import com.ipec.trazactivo.repository.TipoAnotacionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoAnotacionServiceImpl implements TipoAnotacionServiceInterface {

    @Autowired
    private TipoAnotacionDao tipoAnotacionDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoAnotacion> listarTodo() {
        return (List<TipoAnotacion>) tipoAnotacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(TipoAnotacion tipoAnotacion) {
        tipoAnotacionDao.save(tipoAnotacion);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(TipoAnotacion tipoAnotacion) {
        tipoAnotacionDao.delete(tipoAnotacion);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoAnotacion encontrarPorId(TipoAnotacion tipoAnotacion) {
        return tipoAnotacionDao.findById(tipoAnotacion.getIdTipoAnotacion()).orElse(null);
    }
    
}
