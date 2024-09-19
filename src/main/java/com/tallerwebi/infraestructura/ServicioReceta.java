package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.dominio.Usuario;

public class ServicioReceta {
    private static final int[] PUNTOS_PARA_NIVEL = {0, 50, 100, 200, 500};
    private static ServicioReceta instance;

public ServicioReceta() {

}
    public static void completarReceta(Usuario usuario, Receta receta) { //Verifica los datos de usuario y recetas, para asignar los puntos correspondientes

        // Validación de que el usuario no sea nulo y la receta tenga puntaje valido
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        } else if (receta.getPuntaje() == 0) {
            throw new IllegalArgumentException("El puntaje de la receta debe estar entre 1 y 5");
        }

        usuario.setPuntos(usuario.getPuntos() + receta.getPuntaje());
        verificarYActualizarNivel(usuario);
    }

    //Verifica en que nivel esta el usuario, y si este supera los valores precargados, sube de nivel
    private static void verificarYActualizarNivel(Usuario usuario) {
        for (int i = PUNTOS_PARA_NIVEL.length - 1; i > 0; i--) {
            if (usuario.getPuntos() >= PUNTOS_PARA_NIVEL[i]) {
                usuario.setNivel(i + 1);
                break;
            }
        }
    }

    public static Receta buscarPorId(int idReceta) {
        return null;
    }


    public static ServicioReceta getInstance() {
        if (instance == null) {
            instance = new ServicioReceta();
        }
        return instance;
    }
}
