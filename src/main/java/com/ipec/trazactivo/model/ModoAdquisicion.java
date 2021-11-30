package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="modo_adquisicion")
public class ModoAdquisicion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modo_adquisicion")
    private int idModoAdquisicion;
    
    @NotEmpty
    @Column(name = "detalle_modo_adquisicion")
    private String detalleModoAdquisicion;
}
