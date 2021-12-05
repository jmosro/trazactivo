package com.ipec.trazactivo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Embeddable
@Data
public class ActivoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "numero_activo")
    Integer numeroActivo;

    @Column(name = "numero_junta")
    @NotNull
    Integer numeroJunta;

    public ActivoPK() {
    }

    public ActivoPK(Integer numeroActivo, Integer numeroJunta) {
        super();
        this.numeroActivo = numeroActivo;
        this.numeroJunta = numeroJunta;
    }

}
