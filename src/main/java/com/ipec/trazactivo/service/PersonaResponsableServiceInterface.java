package com.ipec.trazactivo.service;

import com.ipec.trazactivo.model.PersonaResponsable;
import java.util.List;

public interface PersonaResponsableServiceInterface {
    public List<PersonaResponsable> listarTodo();
    public void guardar(PersonaResponsable personaResponsable);
    public void eliminar(PersonaResponsable personaResponsable);
    public PersonaResponsable encontrarPorId(Integer idPersonaResponsable);
}