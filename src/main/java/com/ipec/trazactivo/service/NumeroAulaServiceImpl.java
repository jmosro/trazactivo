package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.NumeroAula;
import com.ipec.trazactivo.repository.NumeroAulaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumeroAulaServiceImpl implements NumeroAulaServiceInterface {

    @Autowired
    private NumeroAulaDao numeroAulaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<NumeroAula> listarTodo() {
        return (List<NumeroAula>) numeroAulaDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(NumeroAula numeroAula) {
        numeroAulaDao.save(numeroAula);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(NumeroAula numeroAula) {
        numeroAulaDao.delete(numeroAula);
    }

    @Override
    @Transactional(readOnly = true)
    public NumeroAula encontrarPorId(Integer idNumeroAula) {
        return numeroAulaDao.findById(idNumeroAula).orElse(null);
    }
    
}
