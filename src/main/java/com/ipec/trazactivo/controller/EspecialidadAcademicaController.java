package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.EspecialidadAcademica;
import com.ipec.trazactivo.service.EspecialidadAcademicaServiceInterface;
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
@RequestMapping("/especialidadacademica")
public class EspecialidadAcademicaController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private EspecialidadAcademicaServiceInterface especialidadAcademicaService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<EspecialidadAcademica> especialidadAcademicaTodo = especialidadAcademicaService.listarTodo();
        model.addAttribute("especialidadacademicatodo", especialidadAcademicaTodo);
        return "especialidadacademica/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(EspecialidadAcademica especialidadAcademica, Model model) {
        model.addAttribute("especialidadacademicaobjeto", especialidadAcademica);
        model.addAttribute("habilitareliminar", false);
        return "especialidadacademica/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid EspecialidadAcademica especialidadAcademica, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "especialidadacademica/modificar";
        }
        especialidadAcademicaService.guardar(especialidadAcademica);
        return "redirect:/especialidadacademica";
    }
    
    @GetMapping("/editar/{idEspecialidadAcademica}")
    public String editar(EspecialidadAcademica especialidadAcademica, Model model) {
        especialidadAcademica = especialidadAcademicaService.encontrarPorId(especialidadAcademica);
        model.addAttribute("especialidadacademicaobjeto", especialidadAcademica);
        model.addAttribute("habilitareliminar", true);
        return "especialidadacademica/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(EspecialidadAcademica especialidadAcademica) {
        especialidadAcademicaService.eliminar(especialidadAcademica);
        return "redirect:/especialidadacademica";
    }
}
