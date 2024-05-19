package com.mercadolibre.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para manejar las vistas Thymeleaf.
 */
@Controller
public class ThymeleafController {

    /**
     * Maneja la solicitud GET para la página de inicio.
     *
     * @return el nombre de la plantilla Thymeleaf a renderizar
     */
    @GetMapping("/home")
    public String index() {
        return "index";
    }

    /**
     * Maneja la solicitud GET para la página de resultados.
     *
     * @param ip el parámetro de la dirección IP proporcionado en la solicitud
     * @param model el modelo de datos para la vista
     * @return el nombre de la plantilla Thymeleaf a renderizar
     */
    @GetMapping("/results")
    public String results(@RequestParam String ip, Model model) {
        model.addAttribute("ip", ip);
        return "results";
    }
}
