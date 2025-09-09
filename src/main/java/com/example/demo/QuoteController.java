package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {

    private final RateLimiter rateLimiter; // ← your class name

    public QuoteController(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter; // ← inject the RateLimiter
    }

    private List<String> quotes = List.of(
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "The only way to do great work is to love what you do. - Steve Jobs",
            "In the middle of every difficulty lies opportunity. - Albert Einstein",
            "Believe you can and you're halfway there. - Theodore Roosevelt"
    );

    private Random random = new Random();

    @GetMapping("/api/quote")
    public String getRandomQuote(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        rateLimiter.checkRateLimit(clientIp); // ← throws exception if over limit

        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}





