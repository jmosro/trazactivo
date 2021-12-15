package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.EspecialidadAcademica;
import com.ipec.trazactivo.service.EspecialidadAcademicaServiceInterface;
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
@RequestMapping("/especialidadacademica")
public class EspecialidadAcademicaController {

    private final Logger logger = LoggerFactory.getLogger(EspecialidadAcademicaController.class);

    @Autowired
    private EspecialidadAcademicaServiceInterface especialidadAcademicaService;

    @GetMapping("")
    public String inicio(Model model) {
        List<EspecialidadAcademica> especialidadAcademicaTodo = especialidadAcademicaService.listarTodo();
        model.addAttribute("especialidadacademicatodo", especialidadAcademicaTodo);
        return "especialidadacademica/index";
    }

    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("especialidadacademicaobjeto") EspecialidadAcademica especialidadAcademica, Model model) {
        model.addAttribute("especialidadacademicaobjeto", especialidadAcademica);
        model.addAttribute("habilitareliminar", false);
        return "especialidadacademica/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("especialidadacademicaobjeto") EspecialidadAcademica especialidadAcademica, Errors errores, Model model) {
        if (errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "especialidadacademica/modificar";
        }
        especialidadAcademicaService.guardar(especialidadAcademica);
        return "redirect:/especialidadacademica";
    }

    @GetMapping("/editar/{idEspecialidadAcademica}")
    public String editar(@ModelAttribute("especialidadacademicaobjeto") EspecialidadAcademica especialidadAcademica, Model model) {
        especialidadAcademica = especialidadAcademicaService.encontrarPorId(especialidadAcademica.getIdEspecialidadAcademica());
        if (especialidadAcademica == null) {
            return "redirect:/especialidadacademica";
        }
        model.addAttribute("especialidadacademicaobjeto", especialidadAcademica);
        model.addAttribute("habilitareliminar", true);
        return "especialidadacademica/modificar";
    }

    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        EspecialidadAcademica especialidadObjeto = especialidadAcademicaService.encontrarPorId(numeroEliminar);
        if (especialidadObjeto != null) {
            try {
                especialidadAcademicaService.eliminar(especialidadObjeto);
            } catch (DataIntegrityViolationException ex) {
                model.addAttribute("especialidadacademicaobjeto", especialidadObjeto);
                model.addAttribute("errorEliminar", true);
                return "especialidadacademica/modificar";
            }
        }
        return "redirect:/especialidadacademica";
    }
}
