package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="especialidad_academica")
public class EspecialidadAcademica implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad_academica")
    private int idEspecialidadAcademica;
    
    @NotEmpty
    @Column(name = "detalle_especialidad_academica")
    private String detalleEspecialidadAcademica;
}
