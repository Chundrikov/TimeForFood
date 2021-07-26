package com.example.consumer.mapper;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.entity.Consumer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsumerMapper {

    public ConsumerDto toConsumerDto(Consumer consumer) {
        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setName(consumer.getName());
        consumerDto.setPhone(consumer.getPhone());
        consumerDto.setEmail(consumer.getEmail());
        consumerDto.setAddress(consumer.getAddress());
        LocalDateTime createdAt = LocalDateTime.now();
        consumerDto.setCreatedAt(createdAt);
        return consumerDto;
    }
}
