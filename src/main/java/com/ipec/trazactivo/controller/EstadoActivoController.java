package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.EstadoActivo;
import com.ipec.trazactivo.service.EstadoActivoServiceInterface;
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
@RequestMapping("/estadoactivo")
public class EstadoActivoController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private EstadoActivoServiceInterface estadoActivoService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<EstadoActivo> estadoActivoTodo = estadoActivoService.listarTodo();
        model.addAttribute("estadoactivotodo", estadoActivoTodo);
        return "estadoactivo/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(EstadoActivo estadoActivo, Model model) {
        model.addAttribute("estadoactivoobjeto", estadoActivo);
        model.addAttribute("habilitareliminar", false);
        return "estadoactivo/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid EstadoActivo estadoActivo, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "estadoactivo/modificar";
        }
        estadoActivoService.guardar(estadoActivo);
        return "redirect:/estadoactivo";
    }
    
    @GetMapping("/editar/{idEstadoActivo}")
    public String editar(EstadoActivo estadoActivo, Model model) {
        estadoActivo = estadoActivoService.encontrarPorId(estadoActivo);
        model.addAttribute("estadoactivoobjeto", estadoActivo);
        model.addAttribute("habilitareliminar", true);
        return "estadoactivo/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(EstadoActivo estadoActivo) {
        estadoActivoService.eliminar(estadoActivo);
        return "redirect:/estadoactivo";
    }
}
