package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="persona_responsable")
public class PersonaResponsable implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_responsable")
    private int idPersonaResponsable;
    
    @NotEmpty
    @Column(name = "detalle_persona_responsable")
    private String detallePersonaResponsable;
}
