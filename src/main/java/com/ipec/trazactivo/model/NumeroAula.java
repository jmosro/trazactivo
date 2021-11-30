package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="numero_aula")
public class NumeroAula implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_numero_aula")
    private int idNumeroAula;
    
    @NotEmpty
    @Column(name = "detalle_numero_aula")
    private String detalleNumeroAula;
}
