package com.example.pautaapi.service.impl;

import com.example.pautaapi.service.RabbitMQService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) {
        this.rabbitTemplate.convertAndSend(queueName, message);
    }
}