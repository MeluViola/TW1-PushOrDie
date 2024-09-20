package com.tallerwebi.dominio.servicios;

import java.util.Map;

public interface ServicioCalculadora {
    int calcularTotalCocteles(int personas, int coctelesPorPersona);
    Map<String, Integer> calcularIngredientes(int cantidadCocteles, Map<String, Integer> receta);
    int convertirABotellas(int cantidadEnMililitros, int capacidadBotella);
}
