package com.example.consumer.service;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ConsumerService {

    private ConsumerRepository repository;

    public Long create() {

    }

    public ConsumerDto update() {

    }

    public ConsumerDto getConsumerById() {

    }

    public List<ConsumerDto> getAllConsumers() {

    }

    public void deleteById() {

    }
}
