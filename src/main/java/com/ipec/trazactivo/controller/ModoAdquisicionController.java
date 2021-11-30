package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.ModoAdquisicion;
import com.ipec.trazactivo.service.ModoAdquisicionServiceInterface;
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
@RequestMapping("/modoadquisicion")
public class ModoAdquisicionController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private ModoAdquisicionServiceInterface modoAdquisicionService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<ModoAdquisicion> modoAdquisicionTodo = modoAdquisicionService.listarTodo();
        model.addAttribute("modoadquisiciontodo", modoAdquisicionTodo);
        return "modoadquisicion/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(ModoAdquisicion modoAdquisicion, Model model) {
        model.addAttribute("modoadquisicionobjeto", modoAdquisicion);
        model.addAttribute("habilitareliminar", false);
        return "modoadquisicion/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid ModoAdquisicion modoAdquisicion, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "modoadquisicion/modificar";
        }
        modoAdquisicionService.guardar(modoAdquisicion);
        return "redirect:/modoadquisicion";
    }
    
    @GetMapping("/editar/{idModoAdquisicion}")
    public String editar(ModoAdquisicion modoAdquisicion, Model model) {
        modoAdquisicion = modoAdquisicionService.encontrarPorId(modoAdquisicion);
        model.addAttribute("modoadquisicionobjeto", modoAdquisicion);
        model.addAttribute("habilitareliminar", true);
        return "modoadquisicion/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(ModoAdquisicion modoAdquisicion) {
        modoAdquisicionService.eliminar(modoAdquisicion);
        return "redirect:/modoadquisicion";
    }
}
