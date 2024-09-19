package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraDeCocteles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
class CalculadoraController {

    private final CalculadoraDeCocteles calculadora;

    public CalculadoraController(CalculadoraDeCocteles calculadora) {
        this.calculadora = calculadora;
    }

    @GetMapping("/calculadora")
    public String mostrarFormulario() {
        return "calculadora";
    }

    @PostMapping("/resultado-calculadora")
    public String calcularIngredientes(
            @RequestParam("cantidadPersonas") int cantidadPersonas,
            @RequestParam("coctelesPorPersona") int coctelesPorPersona,
            @RequestParam Map<String, String> coctelesSeleccionados,
            Model model) {

        int totalCocteles = calculadora.calcularTotalCocteles(cantidadPersonas, coctelesPorPersona);

        Map<String, Integer> coctelesSeleccionadosInt = coctelesSeleccionados.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Integer.parseInt(e.getValue())));

        Map<String, Integer> ingredientes = calculadora.calcularIngredientes(totalCocteles, coctelesSeleccionadosInt);

        model.addAttribute("totalCocteles", totalCocteles);
        model.addAttribute("ingredientes", ingredientes);

        return "resultado-calculadora";
    }

}