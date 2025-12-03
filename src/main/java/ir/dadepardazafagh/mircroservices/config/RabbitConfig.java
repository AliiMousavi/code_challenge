package ir.dadepardazafagh.mircroservices.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "massive.exchange";
    public static final String QUEUE = "cleanup.queue";
    public static final String ROUTING_KEY = "cleanup.trigger";

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
