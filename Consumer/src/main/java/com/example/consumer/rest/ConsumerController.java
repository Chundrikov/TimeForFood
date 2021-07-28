package com.example.consumer.rest;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = {"consumers"})
@SwaggerDefinition(tags = {
        @Tag(name = "consumers", description = "Endpoint for consumer management")
})
public class ConsumerController {

    private ConsumerService service;

    public ConsumerController(ConsumerService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all consumers",
            notes = "This method presents a list of all consumers")
    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDto>> getAllConsumers() {
        return new ResponseEntity<>(service.getAllConsumers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get consumer by id",
            notes = "This method presents the consumer by id")
    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerDto> getConsumersById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getConsumerById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new consumer",
            notes = "This method creates new consumer")
    @PostMapping("/consumers")
    public ResponseEntity<Long> createConsumers(@RequestParam String name,
                                                @RequestParam String phone,
                                                @RequestParam String email,
                                                @RequestParam String address) {
        return new ResponseEntity(service.create(name, phone, email, address), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update consumer by id",
            notes = "This method updates consumers details")
    @PutMapping("/consumers/{id)")
    @ResponseBody
    public ResponseEntity<Long> updateConsumer(@Valid @RequestBody ConsumerDto consumerDto,
                                               @PathVariable Long id) {
        return new ResponseEntity(service.update(consumerDto, id), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete consumer by id",
            notes = "This method deletes the consumer by id")
    @DeleteMapping("/consumers/{id}")
    public ResponseEntity deleteConsumer(@PathVariable Long id) {

        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
