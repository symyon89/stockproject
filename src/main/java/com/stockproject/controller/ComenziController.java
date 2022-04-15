package com.stockproject.controller;


import com.stockproject.dto.ComenziDto;
import com.stockproject.service.ComenziService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.stockproject.config.MessegeQueueConfig.REZULTATE_COMENZI;

@Component
@Slf4j
@RequiredArgsConstructor
public class ComenziController {

    private final ComenziService comenziService;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @RabbitListener(queues = "COMENZI")
    public void messageListener(ComenziDto comenziDto) {
        log.info("Order recived : " + comenziDto);
        rabbitTemplate.convertAndSend(exchange.getName(), REZULTATE_COMENZI, comenziService.processOrder(comenziDto));
    }


}
