package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraDeCocteles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculadoraControllerTest {

    private CalculadoraController controller;
    private CalculadoraDeCocteles calculadora;

    @BeforeEach
    void setup() {
        calculadora = new CalculadoraDeCocteles();
        controller = new CalculadoraController(calculadora);
    }

    @Test
    void testMostrarFormulario() {
        String viewName = controller.mostrarFormulario();
        assertEquals("calculadora", viewName);
    }

    @Test
    void testCalcularIngredientes() {
        Model model = new ConcurrentModel();

        int cantidadPersonas = 5;
        int coctelesPorPersona = 2;
        Map<String, String> coctelesSeleccionados = new HashMap<>();
        coctelesSeleccionados.put("Mojito", "2");
        coctelesSeleccionados.put("Margarita", "3");

        String viewName = controller.calcularIngredientes(cantidadPersonas, coctelesPorPersona, coctelesSeleccionados, model);

        assertEquals("resultado-calculadora", viewName);

        assertNotNull(model.getAttribute("totalCocteles"));
        assertNotNull(model.getAttribute("ingredientes"));

        int totalCocteles = (int) model.getAttribute("totalCocteles");
        assertEquals(10, totalCocteles);
    }
}
