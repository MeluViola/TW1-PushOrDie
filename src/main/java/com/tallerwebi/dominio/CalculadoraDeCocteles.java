package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class CalculadoraDeCocteles {

    // Metodo que calcula la cantidad de cócteles totales
    public int calcularTotalCocteles(int personas, int coctelesPorPersona) {
        // Multiplicamos la cantidad de personas por los cócteles x persona
        return personas * coctelesPorPersona;
    }

    // Metodo para calcular ingredientes por cóctel
    public Map<String, Integer> calcularIngredientes(int cantidadCocteles, Map<String, Integer> receta) {
        Map<String, Integer> totalIngredientes = new HashMap<>();

        // se busca en la receta y multiplica cada ingrediente por la cantidad de cócteles
        for (Map.Entry<String, Integer> entry : receta.entrySet()) {
            totalIngredientes.put(entry.getKey(), entry.getValue() * cantidadCocteles);
        }

        return totalIngredientes;
    }

    // Metodo para convertir ml a botellas de cierta capacidad
    public int convertirABotellas(int cantidadEnMililitros, int capacidadBotella) {
        // Dividimos la cantidad total de mililitros entre la capacidad de la botella
        // y usamos Math.ceil para redondear hacia arriba (ya que no se puede comprar media botella)
        return (int) Math.ceil((double) cantidadEnMililitros / capacidadBotella);
    }
}
