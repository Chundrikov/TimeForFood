package com.example.consumer.service;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.entity.Consumer;
import com.example.consumer.mapper.ConsumerMapper;
import com.example.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ConsumerService {

    private ConsumerRepository repository;

    private ConsumerMapper mapper;

    public Long create(String name, String phone, String email, String address) {
        Consumer newConsumer = repository.save(new Consumer(name, phone, email, address));
        return newConsumer.getId();
    }

    public ConsumerDto update(Long id, ConsumerDto consumerDto) {
        Consumer created = mapper.toConsumer(consumerDto);
        Consumer updatedConsumer = repository.findById(id).orElseThrow(()
                -> new ItemNotFoundException(id));
    }

    public ConsumerDto getConsumerById() {

    }

    public List<ConsumerDto> getAllConsumers() {

    }

    public void deleteById() {

    }
}
