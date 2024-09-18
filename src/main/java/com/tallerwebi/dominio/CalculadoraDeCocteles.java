package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class CalculadoraDeCocteles {

    // Método que calcula la cantidad de cócteles totales
    public int calcularTotalCocteles(int personas, int coctelesPorPersona) {
        // Multiplicamos la cantidad de personas por los cócteles por persona
        return personas * coctelesPorPersona;
    }

    // Método para calcular ingredientes por cóctel
    public Map<String, Integer> calcularIngredientes(int cantidadCocteles, Map<String, Integer> receta) {
        Map<String, Integer> totalIngredientes = new HashMap<>();

        // Recorremos la receta y multiplicamos cada ingrediente por la cantidad de cócteles
        for (Map.Entry<String, Integer> entry : receta.entrySet()) {
            totalIngredientes.put(entry.getKey(), entry.getValue() * cantidadCocteles);
        }

        return totalIngredientes;
    }

    // Método para convertir ml a botellas de cierta capacidad
    public int convertirABotellas(int cantidadEnMililitros, int capacidadBotella) {
        // Dividimos la cantidad total de mililitros entre la capacidad de la botella
        // y usamos Math.ceil para redondear hacia arriba (ya que no puedes comprar media botella)
        return (int) Math.ceil((double) cantidadEnMililitros / capacidadBotella);
    }
}
