package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="estado_activo")
public class EstadoActivo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_activo")
    private int idEstadoActivo;
    
    @NotEmpty
    @Column(name = "detalle_estado_activo")
    private String detalleEstadoActivo;
}
