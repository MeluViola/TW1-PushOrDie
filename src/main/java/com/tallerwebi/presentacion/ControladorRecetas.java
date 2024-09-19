package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.infraestructura.ServicioReceta;
import com.tallerwebi.infraestructura.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/recetas")

public class ControladorRecetas {
    private ServicioReceta servicioReceta;

    public ControladorRecetas(ServicioReceta servicioReceta) {
        this.servicioReceta = servicioReceta;
    }

    @PostMapping("/completar") //se usa para manejar formularios o cualquier solicitud POST
    public String completarReceta(@RequestParam("idUsuario") int idUsuario, @RequestParam("idReceta") int idReceta, Model model) { //captura los valores que se pasan en la solicitud POST
        Usuario usuario = ServicioUsuario.buscarPorId(idUsuario);
        Receta receta = ServicioReceta.buscarPorId(idReceta);

        ServicioReceta.completarReceta(usuario, receta); //Busca en el servicioUsuario

        model.addAttribute("usuario", usuario); //agrega el objeto usuario actualizado al modelo
        return "resultadoReceta";  // Redirige a una vista que muestre el resultado
    }
}

