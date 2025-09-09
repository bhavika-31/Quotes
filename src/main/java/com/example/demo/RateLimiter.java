package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiter {
    private final Map<String, UserRequestInfo> requestMap = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 5;
    private static final long TIME_WINDOW = 60 * 1000; 

    public void checkRateLimit(String clientIp) {
        long currentTime = System.currentTimeMillis();
        requestMap.putIfAbsent(clientIp, new UserRequestInfo(0, currentTime));
        UserRequestInfo info = requestMap.get(clientIp);

       
        if (currentTime - info.startTime > TIME_WINDOW) {
            info.count = 0;
            info.startTime = currentTime;
        }

        if (info.count >= MAX_REQUESTS) {
            long waitTimeMs = TIME_WINDOW - (currentTime - info.startTime);
            long waitTimeSec = waitTimeMs / 1000;
            throw new RateLimitExcedException(
                    "Rate limit exceeded. Try again in " + waitTimeSec + " seconds."
            );
        }

        info.count++;
    }

    private static class UserRequestInfo {
        int count;
        long startTime;

        UserRequestInfo(int count, long startTime) {
            this.count = count;
            this.startTime = startTime;
        }
    }
}

