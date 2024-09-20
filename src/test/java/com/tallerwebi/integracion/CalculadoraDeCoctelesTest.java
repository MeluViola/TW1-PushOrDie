package com.tallerwebi.integracion;

import com.tallerwebi.dominio.servicios.ServicioCalculadora;
import com.tallerwebi.dominio.serviciosImpl.ServicioCalculadoraImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class ServicioCalculadoraImplTest {
    private ServicioCalculadora servicioCalculadoraImpl;

    @BeforeEach
    public void setUp() {
        servicioCalculadoraImpl = new ServicioCalculadoraImpl();
    }

    @Test
    public void dadoQueExisteUnaCalculadoraDeIngredientesQueCuandoIngresoLaCantidadDeCincuentaPersonasYLaCantidadDeTresCoctelesPorPersonaMeDevuelvaLaCantidadTotalDeCoctelesARealizar() {

        // Caso: 50 personas y 3 cócteles por persona
        int totalCocteles = this.servicioCalculadoraImpl.calcularTotalCocteles(50, 3);

        // Debería calcular 150 cócteles
        assertEquals(150, totalCocteles);
    }

    @Test
    public void dadoQueExisteUnaCalculadoraDeIngredientesCuandoCalculoLosIngredientesParaUnTragoMojitoEntoncesObjetgoLosIngredientesRonYJugoDelima() {

        // Receta para Mojito: 50 ml de ron y 30 ml de jugo de lima
        Map<String, Integer> recetaMojito = new HashMap<>();
        recetaMojito.put("ron", 50);  // 50 ml de ron
        recetaMojito.put("jugo de lima", 30);  // 30 ml de jugo de lima

        // Calcula ingredientes para 60 Mojitos
        Map<String, Integer> ingredientesTotales = this.servicioCalculadoraImpl.calcularIngredientes(60, recetaMojito);

        // Verificaciones
        assertEquals(3000, ingredientesTotales.get("ron"));  // 60 Mojitos * 50 ml = 3000 ml
        assertEquals(1800, ingredientesTotales.get("jugo de lima"));  // 60 Mojitos * 30 ml = 1800 ml
    }

    @Test
    public void dadoQueExisteUnaCalculadoraDeIngredientesCuandoIngresoLaCantidadDeTresMilMlDeRonMeDevuelvaLaConversionDeDichaCantidadEnNroDeBotellasAComprar() {

        // 3000 ml de ron, con botellas de 750 ml
        int botellas = this.servicioCalculadoraImpl.convertirABotellas(3000, 750);

        // Debería calcular 4 botellas
        assertEquals(4, botellas);

    }

    @Test
    public void dadoQueExisteUnaCalculadoraDeIngredientesCuandoIngresoLaCantidadDeMilMlDeJugoDeLimaMeDevuelvaLaConversionDeDichaCantidadEnNroDeBotellasAComprar() {

        // 1800 ml de jugo de lima, botellas de 1000 ml
        int botellasLima = this.servicioCalculadoraImpl.convertirABotellas(1800, 1000);

        // Debería calcular 2 botellas
        assertEquals(2, botellasLima);
    }

    @Test
    public void  dadoQueExisteUnaCalculadoraDeIngredientesCuandoIngresoMasDeUnaRecetaCalculeLosIngredientesNecesaciosYVerificaLaCorrectaAsignacionDeCantidades() {

        // Receta para Mojito: 50 ml de ron y 30 ml de jugo de lima
        Map<String, Integer> recetaMojito = new HashMap<>();
        recetaMojito.put("ron", 50);
        recetaMojito.put("jugo de lima", 30);

        // Receta para Margarita: 40 ml de tequila, 20 ml de jugo de limón
        Map<String, Integer> recetaMargarita = new HashMap<>();
        recetaMargarita.put("tequila", 40);
        recetaMargarita.put("jugo de limón", 20);

        // Calcula ingredientes para 40 Mojitos y 50 Margaritas
        Map<String, Integer> ingredientesTotalesMojito = this.servicioCalculadoraImpl.calcularIngredientes(40, recetaMojito);
        Map<String, Integer> ingredientesTotalesMargarita = this.servicioCalculadoraImpl.calcularIngredientes(50, recetaMargarita);

        // Verificación para Mojitos
        assertEquals(2000, ingredientesTotalesMojito.get("ron"));  // 40 Mojitos * 50 ml
        assertEquals(1200, ingredientesTotalesMojito.get("jugo de lima"));  // 40 Mojitos * 30 ml

        // Verificación para Margaritas
        assertEquals(2000, ingredientesTotalesMargarita.get("tequila"));  // 50 Margaritas * 40 ml
        assertEquals(1000, ingredientesTotalesMargarita.get("jugo de limón"));  // 50 Margaritas * 20 ml
    }

    @Test
    public void  dadoQueExisteUnaCalculadoraDeIngredientesCuandoIngresoLosIngredientesDeUnaRecetaYLaCantidadDeCopasAPrepararMeDevuelvaLaCantidadDeIngredientesConUnMargenDelDiezPorcientoAgregado() {

        // Receta para Daiquiri: 60 ml de ron, 25 ml de jugo de lima
        Map<String, Integer> recetaDaiquiri = new HashMap<>();
        recetaDaiquiri.put("ron", 60);
        recetaDaiquiri.put("jugo de lima", 25);

        // Cantidad de Daiquiris a preparar
        int cantidadDaiquiris = 30;

        // Calcula ingredientes para 30 Daiquiris
        Map<String, Integer> ingredientesTotales = this.servicioCalculadoraImpl.calcularIngredientes(cantidadDaiquiris, recetaDaiquiri);

        // Aplica un margen de seguridad del 10%
        double margen = 1.1;
        ingredientesTotales.put("ron", (int) (ingredientesTotales.get("ron") * margen));
        ingredientesTotales.put("jugo de lima", (int) (ingredientesTotales.get("jugo de lima") * margen));

        // Verificaciones con margen del 10%
        assertEquals(1980, ingredientesTotales.get("ron"));  // (30 * 60) * 1.1 = 1980 ml
        assertEquals(825, ingredientesTotales.get("jugo de lima"));  // (30 * 25) * 1.1 = 825 ml
    }

    @Test
    public void  dadoQueExisteUnaCalculadoraDeIngredientesCuandoIngresoUnaCantidadDeDosMilTrescientosMlDeRonMeDevuelvaLaCantidadDeBotellasConCapacidadNoEstandar() {

        // Cantidad de mililitros de un ingrediente (ejemplo: 2300 ml de ron)
        int cantidadEnMililitros = 2300;

        // Convertir a botellas de capacidad no estándar (ejemplo: botellas de 500 ml)
        int capacidadBotella = 500;
        int botellas = this.servicioCalculadoraImpl.convertirABotellas(cantidadEnMililitros, capacidadBotella);

        // Debería calcular 5 botellas (2300 ml / 500 ml = 4.6, redondea a 5)
        assertEquals(5, botellas);
    }
}
