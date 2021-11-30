package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.TipoAnotacion;
import com.ipec.trazactivo.service.TipoAnotacionServiceInterface;
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
@RequestMapping("/tipoanotacion")
public class TipoAnotacionController {
    
    private final Logger logger = LoggerFactory.getLogger(DescripcionActivoController.class);
    
    @Autowired
    private TipoAnotacionServiceInterface tipoAnotacionService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<TipoAnotacion> tipoAnotacionTodo = tipoAnotacionService.listarTodo();
        model.addAttribute("tipoanotaciontodo", tipoAnotacionTodo);
        return "tipoanotacion/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(TipoAnotacion tipoAnotacion, Model model) {
        model.addAttribute("tipoanotacionobjeto", tipoAnotacion);
        model.addAttribute("habilitareliminar", false);
        return "tipoanotacion/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid TipoAnotacion tipoAnotacion, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "tipoanotacion/modificar";
        }
        tipoAnotacionService.guardar(tipoAnotacion);
        return "redirect:/tipoanotacion";
    }
    
    @GetMapping("/editar/{idTipoAnotacion}")
    public String editar(TipoAnotacion tipoAnotacion, Model model) {
        tipoAnotacion = tipoAnotacionService.encontrarPorId(tipoAnotacion);
        model.addAttribute("tipoanotacionobjeto", tipoAnotacion);
        model.addAttribute("habilitareliminar", true);
        return "tipoanotacion/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(TipoAnotacion tipoAnotacion) {
        tipoAnotacionService.eliminar(tipoAnotacion);
        return "redirect:/tipoanotacion";
    }
}
