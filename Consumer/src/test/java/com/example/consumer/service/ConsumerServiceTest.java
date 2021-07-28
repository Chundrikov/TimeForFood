package com.example.consumer.service;

import com.example.consumer.ConsumerApplication;
import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.entity.Consumer;
import com.example.consumer.exception.ConsumerNotFoundException;
import com.example.consumer.mapper.ConsumerMapper;
import com.example.consumer.repository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
class ConsumerServiceTest {

    @Autowired
    ConsumerService service;

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ConsumerMapper mapper;

    @Test
    public void createConsumer() {
        Consumer consumerCreated = new Consumer(2L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");

        ConsumerDto consumerDto = mapper.toConsumerDto(consumerCreated);
        Long id = service.create(consumerCreated.getName(), consumerCreated.getPhone(), consumerCreated.getEmail(), consumerCreated.getAddress());
        assertEquals(id, consumerCreated.getId());
    }

    @Test
    public void getAllConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(new Consumer(2L, "Vasiya", "79865693214", "example@yandex.ru", "Test street"));
        consumers.add(new Consumer(3L, "Vasiya", "79865693214", "example@yandex.ru", "Test street"));

        List<Consumer> finded = repository.findAll();
        List<ConsumerDto> consumerDtoList = service.getAllConsumers();
        List<ConsumerDto> createdListDto = mapper.toListConsumersDto(consumers);
        assertEquals(consumerDtoList.get(0).getId(), createdListDto.get(0).getId());
        assertEquals(consumerDtoList.get(0).getName(), createdListDto.get(0).getName());
        assertEquals(consumerDtoList.get(0).getPhone(), createdListDto.get(0).getPhone());
    }

    @Test
    public void getConsumerById() throws Exception{
        Consumer consumerCreated = new Consumer(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        ConsumerDto consumerDto = mapper.toConsumerDto(consumerCreated);

        Consumer findedByRepository = repository.getById(1L);
        ConsumerDto findedByService = service.getConsumerById(1L);
        ConsumerDto resultConsumer = mapper.toConsumerDto(consumerCreated);

        assertEquals(findedByService.getId(), resultConsumer.getId());
        assertEquals(findedByService.getName(), resultConsumer.getName());
        assertEquals(findedByService.getPhone(), resultConsumer.getPhone());
    }

    @Test
    public void getConsumerByIdWhenIdIsWrong() {
        Exception exception = assertThrows(ConsumerNotFoundException.class, () -> {
            ConsumerDto resultConsumer = service.getConsumerById(4L);
        });
    }

    @Test
    public void deleteConsumerById() {
        Consumer consumerCreated = new Consumer(2L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(2L);
            service.deleteById(2L);
        });
    }

    @Test
    public void deleteConsumerWhenIdNotFound() {
        assertThrows(UnexpectedRollbackException.class,
                () -> {service.deleteById(56L);});
    }

    @Test
    public void updateConsumer() {
        Consumer consumerCreated = new Consumer(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        ConsumerDto created = mapper.toConsumerDto(consumerCreated);

        Consumer updated = repository.findById(1L).orElseThrow();
        updated.setName(consumerCreated.getName());
        repository.save(updated);

        assertEquals(updated.getName(), created.getName());
    }

}