package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="activo")
public class Activo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ActivoPK activoPK;
   
    private Integer tomo;
    private Integer folio;
    private Integer asiento;
    private String marca;
    private String modelo;
    private String serie;
    @Column(name = "en_reparacion")
    private Boolean enReparacion;
    
    @Column(name = "id_descripcion_activo")
    private Integer idDescripcionActivo;
    @Column(name = "id_estado_activo")
    private Integer idEstadoActivo;
    @Column(name = "id_numero_aula")
    private Integer idNumeroAula;
    @Column(name = "id_modo_adquisicion")
    private Integer idModoAdquisicion;
    
}
