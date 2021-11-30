package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="descripcion_activo")
public class DescripcionActivo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descripcion_activo")
    private int idDescripcionActivo;
    
    @NotEmpty
    @Column(name = "detalle_descripcion_activo")
    private String detalleDescripcionActivo;
}
