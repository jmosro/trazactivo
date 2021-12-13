package com.ipec.trazactivo.controller;

import com.ipec.trazactivo.model.Activo;
import com.ipec.trazactivo.model.ActivoPK;
import com.ipec.trazactivo.model.DescripcionActivo;
import com.ipec.trazactivo.model.EstadoActivo;
import com.ipec.trazactivo.model.ModoAdquisicion;
import com.ipec.trazactivo.model.NumeroAula;
import com.ipec.trazactivo.service.ActivoObservacionServiceInterface;
import com.ipec.trazactivo.service.ActivoServiceInterface;
import com.ipec.trazactivo.service.DescripcionActivoServiceInterface;
import com.ipec.trazactivo.service.EstadoActivoServiceInterface;
import com.ipec.trazactivo.service.ModoAdquisicionServiceInterface;
import com.ipec.trazactivo.service.NumeroAulaServiceInterface;
import java.util.Collections;
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
    @Autowired
    private ActivoObservacionServiceInterface activoObservacionService;

    @GetMapping("")
    public String inicio(Model model) {
        List<Activo> activoTodo = activoService.listarTodo();
        model.addAttribute("activotodo", activoTodo);
        return "activo/index";
    }

    @GetMapping("/agregar")
    public String agregar(@ModelAttribute("activoobjeto") Activo activo, Model model) {
        model.addAttribute("activoobjeto", activo);
        model.addAttribute("habilitareliminar", false);
        model.addAttribute("habilitareditarpkjunta", true);
        model.addAttribute("habilitareditaranotaciones", false);

        var descripcionActivo = descripcionActivoService.listarTodo();
        var estadoActivo = estadoActivoService.listarTodo();
        var numeroAula = numeoAulaService.listarTodo();
        var modoAdquisicion = modoAdquisicionService.listarTodo();
        // Ordenar alfabetico
        Collections.sort(descripcionActivo, (DescripcionActivo value1, DescripcionActivo value2)
                -> value1.getDetalleDescripcionActivo().compareTo(value2.getDetalleDescripcionActivo()));
        Collections.sort(estadoActivo, (EstadoActivo value1, EstadoActivo value2)
                -> value1.getDetalleEstadoActivo().compareTo(value2.getDetalleEstadoActivo()));
        Collections.sort(numeroAula, (NumeroAula value1, NumeroAula value2)
                -> value1.getDetalleNumeroAula().compareTo(value2.getDetalleNumeroAula()));
        Collections.sort(modoAdquisicion, (ModoAdquisicion value1, ModoAdquisicion value2)
                -> value1.getDetalleModoAdquisicion().compareTo(value2.getDetalleModoAdquisicion()));
        model.addAttribute("descripcionactivolista", descripcionActivo);
        model.addAttribute("estadoactivolista", estadoActivo);
        model.addAttribute("numeroaulalista", numeroAula);
        model.addAttribute("modoadquisicionlista", modoAdquisicion);

        return "activo/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("activoobjeto") Activo activo,
            BindingResult errores, Model model) {
        //if(activo.getActivoPK().getNumeroJunta() == null)
        //    errores.addError(new ObjectError("activoobjeto.activoPK.numeroJunta", "error null junta"));
        if (errores.hasErrors()) {
            if (activo.getMarca() == "") {
                activo.setMarca("no indica");
            }
            if (activo.getModelo() == "") {
                activo.setModelo("no indica");
            }
            if (activo.getSerie() == "") {
                activo.setSerie("no indica");
            }
            model.addAttribute("activoobjeto", activo);

            if (activo.getActivoPK().getNumeroActivo() == null) {
                model.addAttribute("habilitareliminar", false);
                model.addAttribute("habilitareditarpkjunta", true);
            } else {
                model.addAttribute("habilitareliminar", true);
                model.addAttribute("habilitareditarpkjunta", false);
            }

            var descripcionActivo = descripcionActivoService.listarTodo();
            var estadoActivo = estadoActivoService.listarTodo();
            var numeroAula = numeoAulaService.listarTodo();
            var modoAdquisicion = modoAdquisicionService.listarTodo();
            // Ordenar alfabetico
            Collections.sort(descripcionActivo, (DescripcionActivo value1, DescripcionActivo value2)
                    -> value1.getDetalleDescripcionActivo().compareTo(value2.getDetalleDescripcionActivo()));
            Collections.sort(estadoActivo, (EstadoActivo value1, EstadoActivo value2)
                    -> value1.getDetalleEstadoActivo().compareTo(value2.getDetalleEstadoActivo()));
            Collections.sort(numeroAula, (NumeroAula value1, NumeroAula value2)
                    -> value1.getDetalleNumeroAula().compareTo(value2.getDetalleNumeroAula()));
            Collections.sort(modoAdquisicion, (ModoAdquisicion value1, ModoAdquisicion value2)
                    -> value1.getDetalleModoAdquisicion().compareTo(value2.getDetalleModoAdquisicion()));
            model.addAttribute("descripcionactivolista", descripcionActivo);
            model.addAttribute("estadoactivolista", estadoActivo);
            model.addAttribute("numeroaulalista", numeroAula);
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
        var estadoActivo = estadoActivoService.listarTodo();
        var numeroAula = numeoAulaService.listarTodo();
        var modoAdquisicion = modoAdquisicionService.listarTodo();
        // Ordenar alfabetico
        Collections.sort(descripcionActivo, (DescripcionActivo value1, DescripcionActivo value2)
                -> value1.getDetalleDescripcionActivo().compareTo(value2.getDetalleDescripcionActivo()));
        Collections.sort(estadoActivo, (EstadoActivo value1, EstadoActivo value2)
                -> value1.getDetalleEstadoActivo().compareTo(value2.getDetalleEstadoActivo()));
        Collections.sort(numeroAula, (NumeroAula value1, NumeroAula value2)
                -> value1.getDetalleNumeroAula().compareTo(value2.getDetalleNumeroAula()));
        Collections.sort(modoAdquisicion, (ModoAdquisicion value1, ModoAdquisicion value2)
                -> value1.getDetalleModoAdquisicion().compareTo(value2.getDetalleModoAdquisicion()));
        model.addAttribute("descripcionactivolista", descripcionActivo);
        model.addAttribute("estadoactivolista", estadoActivo);
        model.addAttribute("numeroaulalista", numeroAula);
        model.addAttribute("modoadquisicionlista", modoAdquisicion);

        /*List<ActivoObservacion> prueba = activo.getActivoObservaciones();
        logger.info("Valores de prueba inicio");
        for (ActivoObservacion valor: prueba) {
            logger.info(valor.getTomo() + "/" + valor.getFolio() + "/" + valor.getAsiento());
        }
        logger.info("Valores de prueba fin");
         */
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
