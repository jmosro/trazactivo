package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.DescripcionActivo;
import com.ipec.trazactivo.service.DescripcionActivoServiceInterface;
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
@RequestMapping("/descripcionactivo")
public class DescripcionActivoController {

    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);

    @Autowired
    private DescripcionActivoServiceInterface descripcionActivoService;

    @GetMapping("")
    public String inicio(Model model) {
        List<DescripcionActivo> descripcionActivoTodo = descripcionActivoService.listarTodo();
        model.addAttribute("descripcionactivotodo", descripcionActivoTodo);
        return "descripcionactivo/index";
    }

    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("descripcionactivoobjeto") DescripcionActivo descripcionActivo, Model model) {
        model.addAttribute("descripcionactivoobjeto", descripcionActivo);
        model.addAttribute("habilitareliminar", false);
        return "descripcionactivo/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("descripcionactivoobjeto") DescripcionActivo descripcionActivo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "descripcionactivo/modificar";
        }
        descripcionActivoService.guardar(descripcionActivo);
        return "redirect:/descripcionactivo";
    }

    @GetMapping("/editar/{idDescripcionActivo}")
    public String editar(@ModelAttribute("descripcionactivoobjeto") DescripcionActivo descripcionActivo, Model model) {
        descripcionActivo = descripcionActivoService.encontrarPorId(descripcionActivo.getIdDescripcionActivo());
        if (descripcionActivo == null) {
            return "redirect:/descripcionactivo";
        }
        model.addAttribute("descripcionactivoobjeto", descripcionActivo);
        model.addAttribute("habilitareliminar", true);
        return "descripcionactivo/modificar";
    }

    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        DescripcionActivo descripcionObjeto = descripcionActivoService.encontrarPorId(numeroEliminar);
        if (descripcionObjeto != null) {
            try {
                descripcionActivoService.eliminar(descripcionObjeto);
            } catch (DataIntegrityViolationException ex) {
                model.addAttribute("descripcionactivoobjeto", descripcionObjeto);
                model.addAttribute("errorEliminar", true);
                return "descripcionactivo/modificar";
            }
        }
        return "redirect:/descripcionactivo";
    }
}
