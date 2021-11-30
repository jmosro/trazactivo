package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="tipo_anotacion")
public class TipoAnotacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_anotacion")
    private int idTipoAnotacion;
    
    @NotEmpty
    @Column(name = "detalle_tipo_anotacion")
    private String detalleTipoAnotacion;
}
