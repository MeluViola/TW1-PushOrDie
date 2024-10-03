package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.serviciosImpl.ServicioCalculadoraImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;

@Controller // controlador de Spring MVC
class CalculadoraController {

    private final ServicioCalculadoraImpl calculadora;

    // Constructor que recibe la dependencia de la CalculadoraDeCocteles e inyecta la instancia
    public CalculadoraController(ServicioCalculadoraImpl calculadora) {
        this.calculadora = calculadora;
    }

    // Método para manejar peticiones GET a "/perfil", que muestra la vista del perfil
    @GetMapping("/perfil")
    public String mostrarPerfil() {
        return "perfil"; // Retorna la vista del perfil
    }

    // Método para manejar el envío del formulario mediante POST a "/perfil"
    @PostMapping("/perfil")
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

        // Agrega los atributos al modelo para mostrarlos en la vista de perfil
        model.addAttribute("totalCocteles", totalCocteles);
        model.addAttribute("ingredientes", ingredientes);

        // Retorna la vista "perfil" que mostrará los resultados
        return "perfil"; // Asegúrate de que esta sea la vista de perfil
    }

    // Método para manejar peticiones GET que muestra el formulario de cálculo
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Si es necesario, puedes agregar datos al modelo que necesites en el formulario, por ejemplo, una lista de cócteles.
        model.addAttribute("mensaje", "Completa el formulario para calcular los ingredientes necesarios para tu evento.");

        // Retorna la vista que contiene el formulario (cambiar "formulario" por el nombre de tu archivo HTML o Thymeleaf correspondiente)
        return "formulario";
    }

}
