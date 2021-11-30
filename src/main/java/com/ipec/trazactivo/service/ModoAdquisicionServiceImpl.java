package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.ModoAdquisicion;
import com.ipec.trazactivo.repository.ModoAdquisicionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModoAdquisicionServiceImpl implements ModoAdquisicionServiceInterface {

    @Autowired
    private ModoAdquisicionDao modoAdquisicionDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<ModoAdquisicion> listarTodo() {
        return (List<ModoAdquisicion>) modoAdquisicionDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(ModoAdquisicion modoAdquisicion) {
        modoAdquisicionDao.save(modoAdquisicion);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(ModoAdquisicion modoAdquisicion) {
        modoAdquisicionDao.delete(modoAdquisicion);
    }

    @Override
    @Transactional(readOnly = true)
    public ModoAdquisicion encontrarPorId(ModoAdquisicion modoAdquisicion) {
        return modoAdquisicionDao.findById(modoAdquisicion.getIdModoAdquisicion()).orElse(null);
    }
    
}
