package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraDeCocteles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
            @RequestParam Map<String, Integer> coctelesSeleccionados,
            Model model) {

        int totalCocteles = calculadora.calcularTotalCocteles(cantidadPersonas, coctelesPorPersona);
        Map<String, Integer> ingredientes = calculadora.calcularIngredientes(totalCocteles, coctelesSeleccionados);

        model.addAttribute("totalCocteles", totalCocteles);
        model.addAttribute("ingredientes", ingredientes);

        return "resultado-calculadora";  // Esta es la vista que mostrará los resultados.
    }
}
