package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.serviciosImpl.ServicioCalculadoraImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculadoraControllerTest {

    private CalculadoraController controller; // Controlador a probar
    private ServicioCalculadoraImpl calculadora; // Dependencia del controlador

    @BeforeEach
    void setup() {
        // Inicializa una instancia de CalculadoraDeCocteles antes de cada prueba
        calculadora = new ServicioCalculadoraImpl();
        // Crea una nueva instancia del controlador inyectando la calculadora
        controller = new CalculadoraController(calculadora);
    }

    @Test
    void queElMetodoMostrarFormularioRetorneElNombreCorrectoDeLaVista() {
        // Prueba que el metodo mostrarFormulario() retorne el nombre correcto de la vista
        String viewName = controller.mostrarFormulario();
        assertEquals("calculadora", viewName); // Verifica que la vista retornada sea "calculadora"
    }

    @Test
    void queAlCrearUnModeloParaPasarLosAtributosEntreElControladorYLaVistaVerifiqueElCorrectoFuncionamientoDeLaCalculadora() {
        // Crea un objeto Model para pasar los atributos entre el controlador y la vista
        Model model = new ConcurrentModel();

        // Datos de prueba
        int cantidadPersonas = 5;
        int coctelesPorPersona = 2;

        // Mapa simulando los cócteles seleccionados por el usuario
        Map<String, String> coctelesSeleccionados = new HashMap<>();
        coctelesSeleccionados.put("Mojito", "2"); // Se seleccionan 2 Mojitos
        coctelesSeleccionados.put("Margarita", "3"); // Se seleccionan 3 Margaritas

        // Llama al metodo calcularIngredientes() del controlador con los datos de prueba
        String viewName = controller.calcularIngredientes(cantidadPersonas, coctelesPorPersona, coctelesSeleccionados, model);

        // Verifica que la vista retornada sea "resultado-calculadora"
        assertEquals("resultado-calculadora", viewName);

        // Verifica que el modelo contenga los atributos necesarios
        assertNotNull(model.getAttribute("totalCocteles"));
        assertNotNull(model.getAttribute("ingredientes"));

        // Verifica que el total de cócteles sea correcto (5 personas * 2 cócteles por persona = 10 cócteles)
        int totalCocteles = (int) model.getAttribute("totalCocteles");
        assertEquals(10, totalCocteles);
    }
}
