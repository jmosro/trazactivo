package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.service.ActivoServiceInterface;
import com.ipec.trazactivo.service.DescripcionActivoServiceInterface;
import com.ipec.trazactivo.service.EstadoActivoServiceInterface;
import com.ipec.trazactivo.service.ModoAdquisicionServiceInterface;
import com.ipec.trazactivo.service.NumeroAulaServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activo")
public class ActivoController {

    private final Logger logger = LoggerFactory.getLogger(ActivoController.class);

    @Autowired
    private ActivoServiceInterface activoService;
    @Autowired
    private DescripcionActivoServiceInterface descripcionActivoService;
    @Autowired
    private EstadoActivoServiceInterface estadoActivoService;
    @Autowired
    private NumeroAulaServiceInterface numeoAulaService;
    @Autowired
    private ModoAdquisicionServiceInterface modoAdquisicionService;

    @GetMapping("")
    public String inicio(Model model) {
        List<Activo> activoTodo = activoService.listarTodo();
        model.addAttribute("activotodo", activoTodo);
        logger.info("Información");
        return "activo/index";
    }

    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("activoobjeto") Activo activo, Model model) {
        model.addAttribute("activoobjeto", activo);
        model.addAttribute("habilitareliminar", false);
        model.addAttribute("habilitareditarpkjunta", true);
        var descripcionActivo = descripcionActivoService.listarTodo();
        model.addAttribute("descripcionactivolista", descripcionActivo);
        var estadoActivo = estadoActivoService.listarTodo();
        model.addAttribute("estadoactivolista", estadoActivo);
        var numeroAula = numeoAulaService.listarTodo();
        model.addAttribute("numeroaulalista", numeroAula);
        var modoAdquisicion = modoAdquisicionService.listarTodo();
        model.addAttribute("modoadquisicionlista", modoAdquisicion);
        return "activo/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("activoobjeto") Activo activo, 
                          BindingResult errores, Model model) {
        //if(activo.getActivoPK().getNumeroJunta() == null)
        //    errores.addError(new ObjectError("activoobjeto.activoPK.numeroJunta", "error null junta"));
        if (errores.hasErrors()) {
            if (activo.getMarca() == "") activo.setMarca("no indica");
            if (activo.getModelo()== "") activo.setModelo("no indica");
            if (activo.getSerie() == "") activo.setSerie("no indica");
            model.addAttribute("activoobjeto", activo);
            
            if (activo.getActivoPK().getNumeroActivo() == null) {
                model.addAttribute("habilitareliminar", false);
                model.addAttribute("habilitareditarpkjunta", true);
            } else {
                model.addAttribute("habilitareliminar", true);
                model.addAttribute("habilitareditarpkjunta", false);
            }
            var descripcionActivo = descripcionActivoService.listarTodo();
            model.addAttribute("descripcionactivolista", descripcionActivo);
            var estadoActivo = estadoActivoService.listarTodo();
            model.addAttribute("estadoactivolista", estadoActivo);
            var numeroAula = numeoAulaService.listarTodo();
            model.addAttribute("numeroaulalista", numeroAula);
            var modoAdquisicion = modoAdquisicionService.listarTodo();
            model.addAttribute("modoadquisicionlista", modoAdquisicion);
            return "activo/modificar";
        }
        activoService.guardar(activo);
        return "redirect:/activo";
    }

    @GetMapping("/editar/{numeroJunta}/{numeroActivo}")
    public String editar(@ModelAttribute("activoobjeto") Activo activo, Model model,
            @PathVariable Integer numeroJunta, @PathVariable Integer numeroActivo) {
        activo = activoService.encontrarPorNumeroActivo(new ActivoPK(numeroActivo, numeroJunta));
        model.addAttribute("activoobjeto", activo);
        model.addAttribute("habilitareliminar", true);
        model.addAttribute("habilitareditarpkjunta", false);
        var descripcionActivo = descripcionActivoService.listarTodo();
        model.addAttribute("descripcionactivolista", descripcionActivo);
        var estadoActivo = estadoActivoService.listarTodo();
        model.addAttribute("estadoactivolista", estadoActivo);
        var numeroAula = numeoAulaService.listarTodo();
        model.addAttribute("numeroaulalista", numeroAula);
        var modoAdquisicion = modoAdquisicionService.listarTodo();
        model.addAttribute("modoadquisicionlista", modoAdquisicion);
        return "activo/modificar";
    }

    @GetMapping("/eliminar/{numeroJunta}/{numeroActivo}")
    public String eliminar(@PathVariable Integer numeroJunta,
            @PathVariable Integer numeroActivo) {
        Activo activo = activoService.encontrarPorNumeroActivo(new ActivoPK(numeroActivo, numeroJunta));
        activoService.eliminar(activo);
        return "redirect:/activo";
    }
}
