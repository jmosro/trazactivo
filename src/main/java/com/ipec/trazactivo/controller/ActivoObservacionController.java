package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.ActivoObservacion;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.service.ActivoObservacionServiceInterface;
import com.ipec.trazactivo.service.TipoAnotacionServiceInterface;
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
@RequestMapping("/activoobservacion")
public class ActivoObservacionController {

    private final Logger logger = LoggerFactory.getLogger(ActivoObservacionController.class);

    @Autowired
    private ActivoObservacionServiceInterface activoObservacionService;
    @Autowired
    private TipoAnotacionServiceInterface tipoAnotacionService;

    /*@GetMapping("/agregar")
    public String agregar(@ModelAttribute("activoobservacionobjeto") ActivoObservacion activoObservacion, Model model) {
        model.addAttribute("activoobservacionobjeto", activoObservacion);
        model.addAttribute("habilitareliminar", false);
        model.addAttribute("habilitarEditarJuntaPlaca", true);
        var tipoAnotacionlista = tipoAnotacionService.listarTodo();
        model.addAttribute("tipoanotacionlista", tipoAnotacionlista);
        return "activoobservacion/modificar";
    }*/
    
    @GetMapping("/agregar/{numeroJunta}/{numeroActivo}")
    public String agregarConJuntaPlaca(@ModelAttribute("activoobservacionobjeto") ActivoObservacion activoObservacion,
                                       @PathVariable Integer numeroJunta, @PathVariable Integer numeroActivo, Model model) {
        activoObservacion = new ActivoObservacion();
        activoObservacion.setActivoPK(new ActivoPK(numeroActivo, numeroJunta));
        model.addAttribute("activoobservacionobjeto", activoObservacion);
        model.addAttribute("habilitareliminar", false);
        model.addAttribute("habilitarEditarJuntaPlaca", false);
        var tipoAnotacionlista = tipoAnotacionService.listarTodo();
        model.addAttribute("tipoanotacionlista", tipoAnotacionlista);
        return "activoobservacion/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("activoobservacionobjeto") ActivoObservacion activoObservacion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            model.addAttribute("habilitareliminar", true);
            model.addAttribute("habilitarEditarJuntaPlaca", false);
            var tipoAnotacionlista = tipoAnotacionService.listarTodo();
            model.addAttribute("tipoanotacionlista", tipoAnotacionlista);
            return "activoobservacion/modificar";
        }
        activoObservacionService.guardar(activoObservacion);
        return "redirect:/activo";
    }

    @GetMapping("/editar/{idActivoObservacion}")
    public String editar(@ModelAttribute("activoobservacionobjeto") ActivoObservacion activoObservacion, Model model) {
        activoObservacion = activoObservacionService.encontrarPorId(activoObservacion.getIdActivoObservacion());
        model.addAttribute("activoobservacionobjeto", activoObservacion);
        model.addAttribute("habilitareliminar", true);
        model.addAttribute("habilitarEditarJuntaPlaca", false);
        var tipoAnotacionlista = tipoAnotacionService.listarTodo();
        model.addAttribute("tipoanotacionlista", tipoAnotacionlista);
        return "activoobservacion/modificar";
    }

    @GetMapping("/eliminar/{numeroEliminar}")
    public String eliminar(@PathVariable Integer numeroEliminar, Model model) {
        ActivoObservacion activoObservacion = activoObservacionService.encontrarPorId(numeroEliminar);
        //try {
            activoObservacionService.eliminar(activoObservacion);
        //} catch (DataIntegrityViolationException ex) {
        //    model.addAttribute("activoobservacionobjeto", activoObservacion);
        //    model.addAttribute("errorEliminar", true);
        //    var tipoAnotacionlista = tipoAnotacionService.listarTodo();
        //    model.addAttribute("tipoanotacionlista", tipoAnotacionlista);
        //    return "activoobservacion/modificar";
        //}
        return "redirect:/activo";
    }
}
