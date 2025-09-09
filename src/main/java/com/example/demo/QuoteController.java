package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QuoteController {

    private final RateLimiter rateLimiter;

    public QuoteController(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    private List<String> quotes = List.of(
        "Arise, awake and stop not till the goal is reached. - Swami Vivekananda",
        "Be the change that you wish to see in the world. - Mahatma Gandhi",
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Excellence happens not by accident. It is a process. - APJ Abdul Kalam",
        "Great dreams of great dreamers are always transcended. - Dr. B.R. Ambedkar"
    );

    private Random random = new Random();

    @GetMapping("/api/quote")
    public Map<String, String> getRandomQuote(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        rateLimiter.checkRateLimit(clientIp); // throws exception if over limit

        int index = random.nextInt(quotes.size());
        Map<String, String> response = new HashMap<>();
        response.put("quote", quotes.get(index));
        return response;
    }
}

