package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.PersonaResponsable;
import com.ipec.trazactivo.service.PersonaResponsableServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personaresponsable")
public class PersonaResponsableController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private PersonaResponsableServiceInterface personaResponsableService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<PersonaResponsable> personaResponsableTodo = personaResponsableService.listarTodo();
        model.addAttribute("personaresponsabletodo", personaResponsableTodo);
        return "personaresponsable/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(PersonaResponsable personaResponsable, Model model) {
        model.addAttribute("personaresponsableobjeto", personaResponsable);
        model.addAttribute("habilitareliminar", false);
        return "personaresponsable/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid PersonaResponsable personaResponsable, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "personaresponsable/modificar";
        }
        personaResponsableService.guardar(personaResponsable);
        return "redirect:/personaresponsable";
    }
    
    @GetMapping("/editar/{idPersonaResponsable}")
    public String editar(PersonaResponsable personaResponsable, Model model) {
        personaResponsable = personaResponsableService.encontrarPorId(personaResponsable);
        model.addAttribute("personaresponsableobjeto", personaResponsable);
        model.addAttribute("habilitareliminar", true);
        return "personaresponsable/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(PersonaResponsable personaResponsable) {
        personaResponsableService.eliminar(personaResponsable);
        return "redirect:/personaresponsable";
    }
}
