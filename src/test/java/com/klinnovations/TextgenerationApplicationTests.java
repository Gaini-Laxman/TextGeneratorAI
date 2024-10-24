package com.klinnovations;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.klinnovations.service.TextGenerationService;

@SpringBootTest
class TextgenerationApplicationTests {

    @Autowired
    private TextGenerationService textGenerationService; // Example service to check

    @Test
     void contextLoads() {
        // Ensure that the application context loads and the service is available
        assertThat(textGenerationService).isNotNull(); // Check that the service bean is loaded
    }
}
