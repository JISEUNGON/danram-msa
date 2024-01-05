package com.danram.alarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class HomeController {
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public void ping(@RequestParam String message, @RequestParam String name) {
        redisTemplate.convertAndSend(name, message);
    }
}
