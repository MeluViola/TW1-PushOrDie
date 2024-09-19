package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraDeCocteles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;

@Controller // controlador de Spring MVC
class CalculadoraController {

    private final CalculadoraDeCocteles calculadora;

    // Constructor que recibe la dependencia de la CalculadoraDeCocteles e inyecta la instancia
    public CalculadoraController(CalculadoraDeCocteles calculadora) {
        this.calculadora = calculadora;
    }

    // Metodo para manejar peticiones GET a "/calculadora", que muestra el formulario de la calculadora
    @GetMapping("/calculadora")
    public String mostrarFormulario() {
        return "calculadora"; // Retorna la vista del formulario "calculadora"
    }

    // Metodo para manejar el envío del formulario mediante POST a "/resultado-calculadora"
    @PostMapping("/resultado-calculadora")
    public String calcularIngredientes(
            @RequestParam("cantidadPersonas") int cantidadPersonas, // Recoge la cantidad de personas del formulario
            @RequestParam("coctelesPorPersona") int coctelesPorPersona, // Recoge la cantidad de cócteles por persona del formulario
            @RequestParam Map<String, String> coctelesSeleccionados, // Recoge un mapa con los cócteles seleccionados y sus cantidades
            Model model) { // El objeto Model permite pasar datos a la vista

        // Calcula el total de cócteles en función de la cantidad de personas y cócteles por persona
        int totalCocteles = calculadora.calcularTotalCocteles(cantidadPersonas, coctelesPorPersona);

        // Convierte los valores del mapa de coctelesSeleccionados de String a Integer
        Map<String, Integer> coctelesSeleccionadosInt = coctelesSeleccionados.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Integer.parseInt(e.getValue())));

        // Calcula la cantidad de ingredientes necesarios en función del total de cócteles y el tipo de cócteles seleccionados
        Map<String, Integer> ingredientes = calculadora.calcularIngredientes(totalCocteles, coctelesSeleccionadosInt);

        // Agrega los atributos al modelo para mostrarlos en la vista
        model.addAttribute("totalCocteles", totalCocteles);
        model.addAttribute("ingredientes", ingredientes);

        // Retorna la vista "resultado-calculadora" que mostrará el resultado de los cálculos
        return "resultado-calculadora";
    }
}
