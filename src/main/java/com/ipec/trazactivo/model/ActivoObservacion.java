package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import lombok.Data;

@Entity
@Data
@Table(name="activo_observacion")
public class ActivoObservacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo_observacion")
    private int idActivoObservacion;
    
    @Embedded
    private @Valid ActivoPK activoPK;
    
    private Integer tomo;
    private Integer folio;
    private Integer asiento;
    
    // @Column(name = "id_tipo_anotacion")
    @JoinColumn(name="id_tipo_anotacion", referencedColumnName = "id_tipo_anotacion")
    @ManyToOne
    private TipoAnotacion tipoAnotacion;
    
    @Column(name = "otro_detalle")
    private String otroDetalle;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(
            name = "numero_activo",
            referencedColumnName = "numero_activo", insertable = false, updatable = false),
        @JoinColumn(
            name = "numero_junta",
            referencedColumnName = "numero_junta", insertable = false, updatable = false)
    })
    private Activo activo;
    
}
