package ee.blakcat.pacyorky.services.notifications.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.blakcat.pacyorky.services.notifications.TelegramNotificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramNotificatorImpl implements TelegramNotificator {

    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.chat}")
    private String chat;

    private final ObjectMapper objectMapper;

    @Autowired
    public TelegramNotificatorImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendNotification(String message) {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).version(HttpClient.Version.HTTP_2).build();
        Map<String, String> params = new HashMap<>();
        params.put("chat_id", chat);
        params.put("text", message);
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofSeconds(5))
                    .uri(URI.create("https://api.telegram.org/bot" + token + "/sendMessage"))
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(params)))
                    .header("Content-Type", "application/json")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (httpRequest != null) {
            try {
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
