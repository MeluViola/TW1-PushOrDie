package com.tallerwebi.dominio.clases;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HistorialReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorialReceta;

    private Date fechaIntento;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "receta_id_receta")
    private Receta receta;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     public Receta getReceta() {return receta;}
}

