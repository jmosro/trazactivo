package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.ModoAdquisicion;
import com.ipec.trazactivo.service.ModoAdquisicionServiceInterface;
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
@RequestMapping("/modoadquisicion")
public class ModoAdquisicionController {
    
    private final Logger logger = LoggerFactory.getLogger(ModoAdquisicionController.class);
    
    @Autowired
    private ModoAdquisicionServiceInterface modoAdquisicionService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<ModoAdquisicion> modoAdquisicionTodo = modoAdquisicionService.listarTodo();
        model.addAttribute("modoadquisiciontodo", modoAdquisicionTodo);
        return "modoadquisicion/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("modoadquisicionobjeto") ModoAdquisicion modoAdquisicion, Model model) {
        model.addAttribute("modoadquisicionobjeto", modoAdquisicion);
        model.addAttribute("habilitareliminar", false);
        return "modoadquisicion/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("modoadquisicionobjeto") ModoAdquisicion modoAdquisicion, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "modoadquisicion/modificar";
        }
        modoAdquisicionService.guardar(modoAdquisicion);
        return "redirect:/modoadquisicion";
    }
    
    @GetMapping("/editar/{idModoAdquisicion}")
    public String editar(@ModelAttribute("modoadquisicionobjeto") ModoAdquisicion modoAdquisicion, Model model) {
        modoAdquisicion = modoAdquisicionService.encontrarPorId(modoAdquisicion.getIdModoAdquisicion());
        model.addAttribute("modoadquisicionobjeto", modoAdquisicion);
        model.addAttribute("habilitareliminar", true);
        return "modoadquisicion/modificar";
    }
    
    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        ModoAdquisicion adquisicionObjeto = modoAdquisicionService.encontrarPorId(numeroEliminar);
        try {
            modoAdquisicionService.eliminar(adquisicionObjeto);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("modoadquisicionobjeto", adquisicionObjeto);
            model.addAttribute("errorEliminar", true);
            return "modoadquisicion/modificar";
        }
        return "redirect:/modoadquisicion";
    }
}
