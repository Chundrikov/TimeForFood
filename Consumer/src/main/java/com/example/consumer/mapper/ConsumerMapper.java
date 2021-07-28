package com.example.consumer.mapper;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.entity.Consumer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerMapper {

    public ConsumerDto toConsumerDto(Consumer consumer) {
        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setName(consumer.getName());
        consumerDto.setPhone(consumer.getPhone());
        consumerDto.setEmail(consumer.getEmail());
        consumerDto.setAddress(consumer.getAddress());
        return consumerDto;
    }

    public List<ConsumerDto> toListConsumersDto (List<Consumer> consumers) {
        List<ConsumerDto> consumerDtos = new ArrayList<>();
        for (Consumer consumer: consumers) {
            ConsumerDto consumerDto = toConsumerDto(consumer);
            consumerDtos.add(consumerDto);
        }
        return consumerDtos;
    }

    public Consumer toConsumer(ConsumerDto consumerDto) {
        Consumer consumer = new Consumer();
        consumer.setName(consumerDto.getName());
        consumer.setAddress(consumerDto.getAddress());
        consumer.setEmail(consumerDto.getEmail());
        consumer.setPhone(consumerDto.getPhone());
        return consumer;
    }
}
