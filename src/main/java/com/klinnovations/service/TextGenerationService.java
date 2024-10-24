package com.klinnovations.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@Service
public class TextGenerationService {

    //@Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient;

    // Rate limiting variables
    private static final long REQUEST_INTERVAL_MS = 1000; // 1 request per second
    private long lastRequestTime = 0;

    public TextGenerationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    public Mono<String> generateText(String prompt) {
        // Implementing rate limiting
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRequestTime < REQUEST_INTERVAL_MS) {
            try {
                Thread.sleep(REQUEST_INTERVAL_MS - (currentTime - lastRequestTime));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        lastRequestTime = System.currentTimeMillis();

        // Create request body
        String requestBody = String.format("{\"model\":\"gpt-3.5-turbo\", \"messages\":[{\"role\":\"user\", \"content\":\"%s\"}]}", prompt);

        // Send request using WebClient
        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    // Handle error response if needed
                    if (e instanceof WebClientResponseException) {
                        WebClientResponseException we = (WebClientResponseException) e;
                        if (we.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                            return Mono.just("Error: Too many requests. Please try again later.");
                        }
                    }
                    return Mono.error(e); // Re-throw for other errors
                });
    }
}
