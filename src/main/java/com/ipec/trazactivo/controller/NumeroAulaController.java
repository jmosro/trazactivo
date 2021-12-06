package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.NumeroAula;
import com.ipec.trazactivo.service.NumeroAulaServiceInterface;
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
@RequestMapping("/numeroaula")
public class NumeroAulaController {
    
    private final Logger logger = LoggerFactory.getLogger(NumeroAulaController.class);
    
    @Autowired
    private NumeroAulaServiceInterface numeroAulaService;
    
    @GetMapping("")
    public String inicio(Model model) {
        List<NumeroAula> numeroAulaTodo = numeroAulaService.listarTodo();
        model.addAttribute("numeroaulatodo", numeroAulaTodo);
        return "numeroaula/index";
    }
    
    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("numeroaulaobjeto") NumeroAula numeroAula, Model model) {
        model.addAttribute("numeroaulaobjeto", numeroAula);
        model.addAttribute("habilitareliminar", false);
        return "numeroaula/modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("numeroaulaobjeto") NumeroAula numeroAula, Errors errores, Model model) {
        if(errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            return "numeroaula/modificar";
        }
        numeroAulaService.guardar(numeroAula);
        return "redirect:/numeroaula";
    }
    
    @GetMapping("/editar/{idNumeroAula}")
    public String editar(@ModelAttribute("numeroaulaobjeto") NumeroAula numeroAula, Model model) {
        numeroAula = numeroAulaService.encontrarPorId(numeroAula.getIdNumeroAula());
        model.addAttribute("numeroaulaobjeto", numeroAula);
        model.addAttribute("habilitareliminar", true);
        return "numeroaula/modificar";
    }
    
    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        NumeroAula aulaObjeto = numeroAulaService.encontrarPorId(numeroEliminar);
        try {
            numeroAulaService.eliminar(aulaObjeto);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("numeroaulaobjeto", aulaObjeto);
            model.addAttribute("errorEliminar", true);
            return "numeroaula/modificar";
        }
        return "redirect:/numeroaula";
    }
}
