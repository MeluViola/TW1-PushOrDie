package com.tallerwebi.dominio;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tallerwebi.infraestructura.ServicioReceta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SumaDePuntosPorRecetaTest {
    private ServicioReceta servicioReceta; //no detectaba el autowired, por ende se definio manualmente el constructor

    @BeforeEach
    public void setUp() {
        servicioReceta = new ServicioReceta();
    }

    @Test
    public void cuandoElusuarioInicieSesionPorPrimeraVezQueTengaLosValoresInicializadosEnCero() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setPuntos(0); // Inicializa los puntos en cero

        //Simula el guardado
        assertThat(usuario.getPuntos(), equalTo(0));

        //Actualizo los puntos manualmente para ver si funciona
        usuario.setPuntos(100);
        assertThat(usuario.getPuntos(), equalTo(100));

        // Vacio los puntos de nuevo a cero
        usuario.setPuntos(0);
        assertThat(usuario.getPuntos(), equalTo(0));
    }


    @Test
    public void cuandoElUsuarioCompletaUnaRecetaSumaLosPuntosCorrespondientes() {
        // Crear un nuevo usuario y asignar 0 puntos
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setPuntos(0); // Inicializa los puntos en cero
        Receta receta = new Receta("Manhattan", 4, 1);

        ServicioReceta.completarReceta(usuario, receta);

        assertThat(usuario.getPuntos(), is(4));
    }

    @Test
    public void cuandoLaRecetaNoTieneUnPuntajeAsignadoQueLanceUnaExcepcion() {
        Usuario usuario = new Usuario();
        Receta receta = new Receta("Mojito", 0, 2); // Puntaje 0 no permitido

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ServicioReceta.completarReceta(usuario, receta);
        });

        assertThat(exception.getMessage(), is("El puntaje de la receta debe estar entre 1 y 5"));
    }

    @Test
    public void cuandoElUsuarioTienePuntajeAcumuladoYCompletaUnaRecetaSumaCorrectamenteTodosLosDiferentesPuntos() {
        Usuario usuario = new Usuario();
        usuario.setPuntos(3); // Usuario ya tiene 3 puntos acumulados
        Receta receta = new Receta("Martini", 2, 3);

        ServicioReceta.completarReceta(usuario, receta);

        assertThat(usuario.getPuntos(), is(5));
    }

    @Test
    public void cuandoUsuarioCompletaVariasRecetas_seAcumulanPuntosCorrectamente() {
        Usuario usuario = new Usuario();
        usuario.setPuntos(0);

        Receta receta1 = new Receta("Margarita", 3, 4);
        Receta receta2 = new Receta("Mojito", 2, 5);
        Receta receta3 = new Receta("Caipirinha", 5, 6);

        ServicioReceta.completarReceta(usuario, receta1);
        ServicioReceta.completarReceta(usuario, receta2);
        ServicioReceta.completarReceta(usuario, receta3);

        assertThat(usuario.getPuntos(), is(10));
    }

    @Test
    public void cuandoSeMarcaUnaRecetaComoCompletaYElUsuarioNoEstabaLogueadoNiRegistradoQueLanceUnaExcepcion() {
        Usuario usuario = null;
        Receta receta = new Receta("Whiskey Sour", 3, 7);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ServicioReceta.completarReceta(usuario, receta);
        });

        assertThat(exception.getMessage(), is("El usuario no puede ser nulo."));
    }


}
