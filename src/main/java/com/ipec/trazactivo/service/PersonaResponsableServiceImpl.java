package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.PersonaResponsable;
import com.ipec.trazactivo.repository.PersonaResponsableDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaResponsableServiceImpl implements PersonaResponsableServiceInterface {

    @Autowired
    private PersonaResponsableDao personaResponsableDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponsable> listarTodo() {
        return (List<PersonaResponsable>) personaResponsableDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(PersonaResponsable personaResponsable) {
        personaResponsableDao.save(personaResponsable);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(PersonaResponsable personaResponsable) {
        personaResponsableDao.delete(personaResponsable);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponsable encontrarPorId(PersonaResponsable personaResponsable) {
        return personaResponsableDao.findById(personaResponsable.getIdPersonaResponsable()).orElse(null);
    }
    
}
