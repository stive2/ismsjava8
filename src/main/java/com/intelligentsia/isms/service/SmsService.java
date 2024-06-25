package com.intelligentsia.isms.service;

import com.intelligentsia.isms.controller.SendSMSGet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SendSMSGet.class);

    @Value("${sms.api.url}")
    private String smsApiUrl;

    @Value("${sms.api.token}")
    private String accessToken;

    @Value("${sms.api.senderID}")
    private String senderID;

    public boolean sendSms(String recipient, String message) {
        // Créer l'objet JSON pour la requête
        String jsonBody = "{\"recipient\":\"" + recipient + "\", \"sender_id\":\"" + senderID + "\", \"type\":\"plain\", \"message\":\"" + message + "\"}";

        // Créer les en-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        // Créer la requête HTTP entity avec le corps JSON et les en-têtes
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        // Créer un objet RestTemplate pour envoyer la requête
        RestTemplate restTemplate = new RestTemplate();

        // Envoyer la requête POST
        logger.info("Envoie de la requete post à ISMS : " + requestEntity);
        try {
            ResponseEntity<String> response = restTemplate.exchange(smsApiUrl, HttpMethod.POST, requestEntity, String.class);

            // Vérifier la réponse de l'API SMS (facultatif)
            HttpStatus statusCode = response.getStatusCode();
            logger.info("Code status : " + statusCode);
            logger.info("Contenu Response : " + response);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            logger.info("Erreur : " + e.getMessage());
            return false;
        }
    }
}
