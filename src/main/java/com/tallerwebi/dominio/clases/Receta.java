package com.tallerwebi.dominio.clases;


public class Receta {
    private String nombre;
    private int idReceta;
    private int puntaje;

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }


public Receta(String nombre, int puntaje, int idReceta) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
