package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.infraestructura.ServicioReceta;
import com.tallerwebi.infraestructura.ServicioUsuario;
import com.tallerwebi.presentacion.ControladorRecetas;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControladorRecetasTest {

    private ControladorRecetas controladorRecetas;
    private ServicioReceta servicioReceta;

    @Before
    public void setUp() {
        servicioReceta = new ServicioReceta();
        controladorRecetas = new ControladorRecetas(servicioReceta); // Inyección manual
    }

}

