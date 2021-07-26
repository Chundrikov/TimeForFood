package com.example.consumer.service;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.entity.Consumer;
import com.example.consumer.exception.ConsumerNotFoundException;
import com.example.consumer.exception.NoDataFoundException;
import com.example.consumer.mapper.ConsumerMapper;
import com.example.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Consumer updatedConsumer = repository.findById(id).orElseThrow(()
                -> new ConsumerNotFoundException(id));
        updatedConsumer.setName(consumerDto.getName());
        updatedConsumer.setPhone(consumerDto.getPhone());
        updatedConsumer.setAddress(consumerDto.getAddress());
        updatedConsumer.setEmail(consumerDto.getEmail());
        repository.save(updatedConsumer);

        ConsumerDto newConsumerDto = mapper.toConsumerDto(updatedConsumer);
        newConsumerDto.setModifiedAt(LocalDateTime.now());
        return newConsumerDto;
    }

    public ConsumerDto getConsumerById(Long id) {
         Consumer consumer = repository.findById(id).orElseThrow(()
                -> new ConsumerNotFoundException(id));
         return mapper.toConsumerDto(consumer);
    }

    public List<ConsumerDto> getAllConsumers() {
        List<Consumer> allConsumers = repository.findAll();
        if(allConsumers.isEmpty()) {
            throw new NoDataFoundException();
        }
        return mapper.toListConsumersDto(allConsumers);
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (Exception e) {
            new ConsumerNotFoundException(id);
        }
    }
}
