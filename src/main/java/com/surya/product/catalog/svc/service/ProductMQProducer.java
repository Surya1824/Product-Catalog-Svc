package com.surya.product.catalog.svc.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.surya.product.catalog.svc.config.RabbitConfig;
import com.surya.product.catalog.svc.model.ProductMQMessage;


@Service
public class ProductMQProducer {
	
    private final RabbitTemplate rabbitTemplate;

    public ProductMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void updateInventory(List<ProductMQMessage> productMessage){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, productMessage);
    }

}
