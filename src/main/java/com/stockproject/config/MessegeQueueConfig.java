package com.stockproject.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MessegeQueueConfig {

    public static final String EXCHANGE = "COMENZI";
    public static final String COMENZI = "COMENZI";
    public static final String REZULTATE_COMENZI = "REZULTATE_COMENZI";

    @Bean
    public Queue comenzi() {
        return new Queue(COMENZI);
    }
    @Bean
    public Queue rezultate() {
        return new Queue(REZULTATE_COMENZI);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);
    }


    @Bean
    public Binding binding(Queue comenzi,DirectExchange exchange){
        return BindingBuilder.bind(comenzi)
                .to(exchange)
                .with(COMENZI);
    }

    @Bean
    public Binding bindingRezultate(Queue rezultate,DirectExchange exchange){
        return BindingBuilder.bind(rezultate)
                .to(exchange)
                .with(REZULTATE_COMENZI);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
