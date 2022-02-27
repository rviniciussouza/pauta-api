package com.example.pautaapi.configs;

import javax.annotation.PostConstruct;

import com.example.pautaapi.constants.RabbitMQ;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {
    private AmqpAdmin amqpAdmin;

    private RabbitMQConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String name) {
        return new Queue(name, true);
    }

    private DirectExchange exchange(String name) {
        return new DirectExchange(name);
    }

    private Binding binding(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void setUp() {
        Queue queuePauta = this.queue(RabbitMQ.QUEUE_PAUTA);
        DirectExchange exchange = this.exchange(RabbitMQ.EXCHANGE_NAME);
        Binding bindingInventory = this.binding(queuePauta, exchange);
        this.amqpAdmin.declareQueue(queuePauta);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(bindingInventory);
    }
}
