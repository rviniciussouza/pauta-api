package com.example.pautaapi.service;

public interface RabbitMQService {
    void sendMessage(String queueName, Object message);
}
