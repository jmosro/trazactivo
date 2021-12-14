package com.ipec.trazactivo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="activo")
public class Activo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private @Valid ActivoPK activoPK;
   
    private Integer tomo;
    private Integer folio;
    private Integer asiento;
    
    @NotEmpty
    private String marca;
    @NotEmpty
    private String modelo;
    @NotEmpty
    private String serie;
    @Column(name = "en_reparacion")
    private Boolean enReparacion;
    
    // @Column(name = "id_descripcion_activo")
    @JoinColumn(name="id_descripcion_activo", referencedColumnName = "id_descripcion_activo")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DescripcionActivo descripcionActivo;
    
    // @Column(name = "id_estado_activo")
    @JoinColumn(name="id_estado_activo", referencedColumnName = "id_estado_activo")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private EstadoActivo estadoActivo;
    
    // @Column(name = "id_numero_aula")
    @JoinColumn(name="id_numero_aula", referencedColumnName = "id_numero_aula")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private NumeroAula numeroAula;
    
    // @Column(name = "id_especialidad_academica")
    @JoinColumn(name="id_especialidad_academica", referencedColumnName = "id_especialidad_academica")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private EspecialidadAcademica especialidadAcademica;
    
    // @Column(name = "id_persona_responsable")
    @JoinColumn(name="id_persona_responsable", referencedColumnName = "id_persona_responsable")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PersonaResponsable personaResponsable;
    
    // @Column(name = "id_modo_adquisicion")
    @JoinColumn(name="id_modo_adquisicion", referencedColumnName = "id_modo_adquisicion")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ModoAdquisicion modoAdquisicion;
    
    @OneToMany(mappedBy = "activo")
    private List<ActivoObservacion> activoObservaciones;
    
}
