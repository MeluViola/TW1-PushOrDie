package com.tallerwebi.dominio.serviciosImpl;

import com.tallerwebi.dominio.clases.Receta;
import com.tallerwebi.dominio.servicios.ServicioReceta;
import com.tallerwebi.dominio.clases.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioRecetaImpl implements ServicioReceta {
  private static final int[] PUNTOS_PARA_NIVEL = {0, 50, 100, 200, 500};


    @Override
    public void completarReceta(Usuario usuario, Receta receta) { //Verifica los datos de usuario y recetas, para asignar los puntos correspondientes

        // Validación de que el usuario no sea nulo y la receta tenga puntaje valido
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        } else if (receta.getPuntaje() == 0) {
            throw new IllegalArgumentException("El puntaje de la receta debe estar entre 1 y 5");
        }

        usuario.setPuntos(usuario.getPuntos() + receta.getPuntaje());
        verificarYActualizarNivel(usuario);
    }

    @Override//Verifica en que nivel esta el usuario, y si este supera los valores precargados, sube de nivel
    public void verificarYActualizarNivel(Usuario usuario) {
        for (int i = PUNTOS_PARA_NIVEL.length - 1; i > 0; i--) {
            if (usuario.getPuntos() >= PUNTOS_PARA_NIVEL[i]) {
                usuario.setNivel(i + 1);
                break;
            }
        }
    }

    @Override
    public Receta buscarPorId(int idReceta) {
        return null;
    }


   /* public static ServicioRecetaImpl getInstance() {
        if (instance == null) {
            instance = new ServicioRecetaImpl();
        }
        return instance;
    }*/

}
