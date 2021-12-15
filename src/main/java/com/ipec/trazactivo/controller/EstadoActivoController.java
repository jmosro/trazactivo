package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.EstadoActivo;
import com.ipec.trazactivo.service.EstadoActivoServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estadoactivo")
public class EstadoActivoController {

    private final Logger logger = LoggerFactory.getLogger(EstadoActivoController.class);

    @Autowired
    private EstadoActivoServiceInterface estadoActivoService;

    @GetMapping("")
    public String inicio(Model model) {
        List<EstadoActivo> estadoActivoTodo = estadoActivoService.listarTodo();
        model.addAttribute("estadoactivotodo", estadoActivoTodo);
        return "estadoactivo/index";
    }

    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("estadoactivoobjeto") EstadoActivo estadoActivo, Model model) {
        model.addAttribute("estadoactivoobjeto", estadoActivo);
        model.addAttribute("habilitareliminar", false);
        return "estadoactivo/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("estadoactivoobjeto") EstadoActivo estadoActivo, BindingResult errores, Model model) {
        if (errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "estadoactivo/modificar";
        }
        estadoActivoService.guardar(estadoActivo);
        return "redirect:/estadoactivo";
    }

    @GetMapping("/editar/{idEstadoActivo}")
    public String editar(@ModelAttribute("estadoactivoobjeto") EstadoActivo estadoActivo, Model model) {
        estadoActivo = estadoActivoService.encontrarPorId(estadoActivo.getIdEstadoActivo());
        if (estadoActivo == null) {
            return "redirect:/estadoactivo";
        }
        model.addAttribute("estadoactivoobjeto", estadoActivo);
        model.addAttribute("habilitareliminar", true);
        return "estadoactivo/modificar";
    }

    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        EstadoActivo estadoObjeto = estadoActivoService.encontrarPorId(numeroEliminar);
        if (estadoObjeto != null) {
            try {
                estadoActivoService.eliminar(estadoObjeto);
            } catch (DataIntegrityViolationException ex) {
                model.addAttribute("estadoactivoobjeto", estadoObjeto);
                model.addAttribute("errorEliminar", true);
                return "estadoactivo/modificar";
            }
        }
        return "redirect:/estadoactivo";
    }
}
