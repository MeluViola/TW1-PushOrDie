package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.clases.Receta;
import com.tallerwebi.dominio.clases.Usuario;

public interface ServicioReceta { //definiciones de metodos
    void completarReceta(Usuario usuario, Receta receta);
    void verificarYActualizarNivel(Usuario usuario);
    Receta buscarPorId(int idReceta);
}
