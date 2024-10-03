package com.tallerwebi.dominio;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tallerwebi.dominio.clases.Receta;
import com.tallerwebi.dominio.clases.Usuario;
import com.tallerwebi.dominio.servicios.ServicioReceta;
import com.tallerwebi.dominio.serviciosImpl.ServicioRecetaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SumaDePuntosPorRecetaTest {
    private ServicioReceta servicioRecetaImpl; //no detectaba el autowired, por ende se definio manualmente el constructor

    @BeforeEach
    public void setUp() {
        servicioRecetaImpl = new ServicioRecetaImpl();
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
        Receta receta = new Receta("Manhattan", 4, "instruccion" );

        this.servicioRecetaImpl.completarReceta(usuario, receta);

        assertThat(usuario.getPuntos(), is(4));
    }

    @Test
    public void cuandoLaRecetaNoTieneUnPuntajeAsignadoQueLanceUnaExcepcion() {
        Usuario usuario = new Usuario();
        Receta receta = new Receta("Mojito", 0, "instruccion"); // Puntaje 0 no permitido

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.servicioRecetaImpl.completarReceta(usuario, receta);
        });

        assertThat(exception.getMessage(), is("El puntaje de la receta debe estar entre 1 y 5"));
    }

    @Test
    public void cuandoElUsuarioTienePuntajeAcumuladoYCompletaUnaRecetaSumaCorrectamenteTodosLosDiferentesPuntos() {
        Usuario usuario = new Usuario();
        usuario.setPuntos(3); // Usuario ya tiene 3 puntos acumulados
        Receta receta = new Receta("Martini", 2, "instruccion");

        this.servicioRecetaImpl.completarReceta(usuario, receta);

        assertThat(usuario.getPuntos(), is(5));
    }

    @Test
    public void cuandoUsuarioCompletaVariasRecetas_seAcumulanPuntosCorrectamente() {
        Usuario usuario = new Usuario();
        usuario.setPuntos(0);

        Receta receta1 = new Receta("Margarita", 3, "instruccion");
        Receta receta2 = new Receta("Mojito", 2, "instruccion");
        Receta receta3 = new Receta("Caipirinha", 5, "instruccion");

        this.servicioRecetaImpl.completarReceta(usuario, receta1);
        this.servicioRecetaImpl.completarReceta(usuario, receta2);
        this.servicioRecetaImpl.completarReceta(usuario, receta3);

        assertThat(usuario.getPuntos(), is(10));
    }

    @Test
    public void cuandoSeMarcaUnaRecetaComoCompletaYElUsuarioNoEstabaLogueadoNiRegistradoQueLanceUnaExcepcion() {
        Usuario usuario = null;
        Receta receta = new Receta("Whiskey Sour", 3, "instruccion");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.servicioRecetaImpl.completarReceta(usuario, receta);
        });

        assertThat(exception.getMessage(), is("El usuario no puede ser nulo."));
    }

    @Test
    public void cuandoElusuarioPaseLosCincuentaPuntosQueSubaANivelDos(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setPuntos(0);

        Receta receta1 = new Receta("Margarita", 5, "instruccion");
        Receta receta2 = new Receta("Mojito", 5, "instruccion");
        Receta receta3 = new Receta("Caipirinha", 5, "instruccion");
        Receta receta4 = new Receta("Margarita", 5, "instruccion");
        Receta receta5 = new Receta("Mojito", 5, "instruccion");
        Receta receta6 = new Receta("Caipirinha", 5, "instruccion");
        Receta receta7 = new Receta("Caipirinha", 5, "instruccion");
        Receta receta8 = new Receta("Margarita", 5, "instruccion");
        Receta receta9 = new Receta("Mojito", 5, "instruccion");
        Receta receta10 = new Receta("Caipirinha", 5, "instruccion");

        this.servicioRecetaImpl.completarReceta(usuario, receta1);
        this.servicioRecetaImpl.completarReceta(usuario, receta2);
        this.servicioRecetaImpl.completarReceta(usuario, receta3);
        this.servicioRecetaImpl.completarReceta(usuario, receta4);
        this.servicioRecetaImpl.completarReceta(usuario, receta5);
        this.servicioRecetaImpl.completarReceta(usuario, receta6);
        this.servicioRecetaImpl.completarReceta(usuario, receta7);
        this.servicioRecetaImpl.completarReceta(usuario, receta8);
        this.servicioRecetaImpl.completarReceta(usuario, receta9);
        this.servicioRecetaImpl.completarReceta(usuario, receta10);

        assertThat(usuario.getPuntos(), is(50));
        assertThat(usuario.getNivel(), is(2));
    }
}
