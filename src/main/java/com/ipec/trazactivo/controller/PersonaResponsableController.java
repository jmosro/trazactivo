package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.PersonaResponsable;
import com.ipec.trazactivo.service.PersonaResponsableServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personaresponsable")
public class PersonaResponsableController {
    
    private final Logger logger = LoggerFactory.getLogger(PersonaResponsableController.class);
    
    @Autowired
    private PersonaResponsableServiceInterface personaResponsableService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<PersonaResponsable> personaResponsableTodo = personaResponsableService.listarTodo();
        model.addAttribute("personaresponsabletodo", personaResponsableTodo);
        return "personaresponsable/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("personaresponsableobjeto") PersonaResponsable personaResponsable, Model model) {
        model.addAttribute("personaresponsableobjeto", personaResponsable);
        model.addAttribute("habilitareliminar", false);
        return "personaresponsable/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("personaresponsableobjeto") PersonaResponsable personaResponsable, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "personaresponsable/modificar";
        }
        personaResponsableService.guardar(personaResponsable);
        return "redirect:/personaresponsable";
    }
    
    @GetMapping("/editar/{idPersonaResponsable}")
    public String editar(@ModelAttribute("personaresponsableobjeto") PersonaResponsable personaResponsable, Model model) {
        personaResponsable = personaResponsableService.encontrarPorId(personaResponsable.getIdPersonaResponsable());
        model.addAttribute("personaresponsableobjeto", personaResponsable);
        model.addAttribute("habilitareliminar", true);
        return "personaresponsable/modificar";
    }
    
    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        PersonaResponsable responsableObjeto = personaResponsableService.encontrarPorId(numeroEliminar);
        try {
            personaResponsableService.eliminar(responsableObjeto);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("personaresponsableobjeto", responsableObjeto);
            model.addAttribute("errorEliminar", true);
            return "personaresponsable/modificar";
        }
        return "redirect:/personaresponsable";
    }
}
