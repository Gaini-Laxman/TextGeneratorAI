package com.klinnovations.restcontroller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klinnovations.service.TextGenerationService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/text")
@CrossOrigin(origins = "http://localhost:4200") // Adjust as needed
public class TextController {

    private final TextGenerationService textGenerationService;

    @Autowired
    public TextController(TextGenerationService textGenerationService) {
        this.textGenerationService = textGenerationService;
    }

    @PostMapping("/generate")
    public Mono<ResponseEntity<TextResponse>> generateText(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");

        if (prompt == null || prompt.isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body(new TextResponse("Prompt must not be empty")));
        }

        return textGenerationService.generateText(prompt)
            .map(generatedText -> ResponseEntity.ok(new TextResponse(generatedText)))
            .onErrorReturn(ResponseEntity.status(500).body(new TextResponse("Error generating text")));
    }
    
    
}
