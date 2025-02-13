package com.spring_jwt_advanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisController")
public class RedisController {
	
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/redis-test")
    public ResponseEntity<String> testRedisConnection() {
    	
        try {        	
            redisTemplate.opsForValue().set("testKey", "Hello, Redis!");
            String value = redisTemplate.opsForValue().get("testKey");
            return ResponseEntity.ok("Redis is working! Retrieved value: " + value);
            
        } catch (Exception e) {        	
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)        			
        			.body("Redis connection failed: " + e.getMessage());
        }
    }

}
