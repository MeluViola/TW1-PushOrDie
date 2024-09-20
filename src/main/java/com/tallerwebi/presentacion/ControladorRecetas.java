package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.clases.Receta;
import com.tallerwebi.dominio.clases.Usuario;
import com.tallerwebi.dominio.servicios.ServicioReceta;
import com.tallerwebi.dominio.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/recetas")

public class ControladorRecetas {
    private ServicioReceta servicioReceta;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorRecetas(ServicioReceta servicioReceta, ServicioUsuario servicioUsuario) {
        this.servicioReceta = servicioReceta;
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping("/completar") //se usa para manejar formularios o cualquier solicitud POST
    public String completarReceta(@RequestParam("idUsuario") int idUsuario, @RequestParam("idReceta") int idReceta, Model model) { //captura los valores que se pasan en la solicitud POST
        Usuario usuario = this.servicioUsuario.buscarPorId(idUsuario);
        Receta receta = this.servicioReceta.buscarPorId(idReceta);

        this.servicioReceta.completarReceta(usuario, receta); //Busca en el servicioUsuario

        model.addAttribute("usuario", usuario); //agrega el objeto usuario actualizado al modelo
        return "resultadoReceta";  // Redirige a una vista que muestre el resultado
    }
}

