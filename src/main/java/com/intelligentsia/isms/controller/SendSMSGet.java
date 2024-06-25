package com.intelligentsia.isms.controller;

import com.intelligentsia.isms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/smsGatwear/rest/sms")
public class SendSMSGet {

    private static final Logger logger = LoggerFactory.getLogger(SendSMSGet.class);
    @Autowired
    private SmsService smsService;

    @GetMapping("/sendSimpleSMS")
    public ResponseEntity<String> handleGetRequest( HttpServletRequest request,
            @RequestParam("phone") String phone,
            @RequestParam("paiementchanel") String paiementchanel,
            @RequestParam("paiementpaterne") String paiementpaterne,
            @RequestParam("Operation") String operation,
            @RequestParam("message") String message) {

        // StringBuilder pour construire la réponse
        StringBuilder sanitizedNumbers = new StringBuilder();
        logger.info("Numéros avant épuration : {}", phone);
        try {
            // Séparer les numéros de téléphone par des virgules
            String[] phoneNumberArray = phone.split(",");

            // Parcourir tous les numéros de téléphone
            for (String phoneNumber : phoneNumberArray) {
                // Supprimer les caractères '0' et '+'
                String sanitizedNumber = phoneNumber.trim().replaceAll("^[0+]+", "");

                // Ajouter à la réponse avec une virgule si nécessaire
                if (sanitizedNumbers.length() > 0) {
                    sanitizedNumbers.append(", ");
                }
                sanitizedNumbers.append(sanitizedNumber);
            }
        } catch (Exception e) {
            logger.error("Echec lors de la suppression des caractères '0' et '+' : {}", e.getMessage());
            sanitizedNumbers = new StringBuilder(phone);
        }
        logger.info("Numéros après épuration : {}", sanitizedNumbers);

        String uri = String.valueOf(request.getRequestURL());
        String address = request.getRemoteAddr();
        String parameters = request.getQueryString();
        // Afficher l'URI dans la console par exemple
        logger.info("URL de la requête : {}", uri);
        logger.info("IP de provenance : {}", address);
        logger.info("Parametres reçu : {}", parameters);
        try {
            logger.info("Appel du service smsService");
            boolean result = smsService.sendSms(String.valueOf(sanitizedNumbers), message);
            logger.info("Résultat retourné : {}", result);

            if (result) {
                // Construire votre objet de réponse JSON
                String jsonResponse = "{\"status\": \"success\", \"message\": \"SMS envoyé avec succès.\", \"Response\": \"1\"}";
                logger.info("SMS envoyé avec succès");
                return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
            } else {
                String jsonResponse = "{\"status\": \"fail\", \"message\": \"Echec lors de l'envoi du SMS.\", \"Response\": \"0\"}";
                logger.error("Echec lors de l'envoi du SMS");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
            }
        } catch (Exception e) {
            String jsonResponse = "{\"status\": \"fail\", \"message\": \"Erreur lors de l'envoi du SMS.\", \"Response\": \"0\"}";
            logger.error("Erreur d'envoie : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
        }
    }
}
