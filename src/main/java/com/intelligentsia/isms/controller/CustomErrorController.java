package com.intelligentsia.isms.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Gérer l'affichage de la page d'erreur personnalisée ou rediriger vers une page d'erreur spécifique
        return "error"; // Assurez-vous d'avoir une vue nommée "error" (error.html, error.jsp, etc.) dans vos ressources
    }

    /*@Override
    public String getErrorPath() {
        return "/error";
    }*/
}
