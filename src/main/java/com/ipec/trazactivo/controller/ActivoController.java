package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.service.ActivoServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activo")
public class ActivoController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private ActivoServiceInterface activoService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<Activo> activoTodo = activoService.listarTodo();
        model.addAttribute("activotodo", activoTodo);
        logger.info("Información");
        return "activo/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Activo activo, Model model) {
        model.addAttribute("activoobjeto", activo);
        model.addAttribute("habilitareliminar", false);
        return "activo/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Activo activo, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "activo/modificar";
        }
        activoService.guardar(activo);
        return "redirect:/activo";
    }
    
    @GetMapping("/editar/{numeroJunta}/{numeroActivo}")
    public String editar(Activo activo, Model model, 
            @PathVariable Integer numeroJunta, @PathVariable Integer numeroActivo) {
        activo = activoService.encontrarPorNumeroActivo(new ActivoPK(numeroActivo, numeroJunta));
        model.addAttribute("activoobjeto", activo);
        model.addAttribute("habilitareliminar", true);
        return "activo/modificar";
    }
    
    @GetMapping("/eliminar/{numeroJunta}/{numeroActivo}")
    public String eliminar(Activo activo, @PathVariable Integer numeroJunta, 
                                          @PathVariable Integer numeroActivo) {
        activo = activoService.encontrarPorNumeroActivo(new ActivoPK(numeroActivo, numeroJunta));
        activoService.eliminar(activo);
        return "redirect:/activo";
    }
}
